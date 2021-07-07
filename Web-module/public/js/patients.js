/*
This javascript file consists of functions that perform READ operations on the DynamoDB database. It also has configuration details and switch-case logic that 
brings interactivity to chart.js charts present in the patients.html file.
*/

// declaring new DynamoDB Client object
let docClient = new AWS.DynamoDB.DocumentClient();
// initialized as a Map so as to facilitate fast look-up times similar to a hash table
let current_intake = new Map();             // stores the number of pills taken up for each medicine (Candid, Envion-600, Pan-40)
let current_health_metrics = new Map();     // stores the BPM and SpO2 levels of the patient


/**
 * A class to facilitate read operations over different medicines.
 * 
 * @constructor
 * @param {String} medicine_name - the name of the medicine
 * @param {Number} patient_id - patient ID number as mentioned in the database.
 * Creates a "params" object that stores DynamoDB related metadata - TableName (to refer the table created in the db) and, 
 * Key (an object to specify which PatientID's record is to be fetched)
 * 
 * @function fetchMedicineIntake
 * Fetches the pills/dosage taken currently for the specified medicine and patient ID.
 * Medicine could be Candid, Envion-600 or Pan-40.
 */
class Medicine{

    constructor(medicine_name, patient_id){
        let medicine_name = medicine_name;
        const table = "EHR";
        const id = String(patient_id);
        const params = {
            TableName: table,
            Key:{
                "PatientID": id
            }
        };
    }

    fetchMedicineIntake(){
        docClient.get(params, function(err, data) {
            if (err) {
              console.log("Unable to read item: " + "\n" + JSON.stringify(err, undefined, 2));
            } else {
              let response = Number(JSON.stringify(data["Item"][medicine_name], undefined, 2));
              console.log(medicine_name + " intake value: " + "\n" + response);
              current_intake.set(medicine_name, response);
            }
        });
        
    }
}


/**
 * A class to facilitate read operation to fetch different metric values.
 * 
 * @constructor
 * @param {String} metric_name - the name of the metric to be fetched
 * @param {Number} patient_id - patient ID number as mentioned in the database
 * Creates a "params" object that stores DynamoDB related metadata - TableName (to refer the table created in the db) and, 
 * Key (an object to specify which PatientID's record is to be fetched)
 * 
 * @function fetchMetricValue
 * Fetches the latest metric value (recorded via the Android app) for the specified patient ID. 
 * Metric could either be BPM or SpO2% of the patient.
 */
class Metric{

    constructor(metric_name, patient_id){
        let metric_name = metric_name;
        const table = "EHR";
        const id = String(patient_id);
        const params = {
            TableName: table,
            Key:{
                "PatientID": id
            }
        };
    }

    fetchMetricValue(){
        docClient.get(params, function(err, data) {
            if (err) {
              console.log("Unable to read item: " + "\n" + JSON.stringify(err, undefined, 2));
            } else {
              let response = Number(JSON.stringify(data["Item"][metric_name], undefined, 2));
              console.log(metric_name + " intake value: " + "\n" + response);
              current_health_metrics.set(metric_name, response);
            }
        });
    }
}

// initialize an instance for each medicine and metric (to be used later)
candid = new Medicine("Candid", 1);
envion_600 = new Medicine("Envion-600", 1);
pan_40 = new Medicine("Pan-40", 1);
bpm = new Metric("BPM", 1);
spo2 = new Metric("SPO2", 1);


/*
Dataset swap function defined below
*/


/**
 * @function swap_medicine_datasets
 * @param {Number} choice - specifies which medicine dataset is to be used
 * This function swaps the dataset based on @param choice and re-renders the chart with the updated dataset values.
 */
 function swap_medicine_datasets(choice) {
    switch (choice) {
        case '1':
            chart.data.datasets[0].label = "Candid";
            chart.data.datasets[0].data = candid_dataset;
            chart.data.datasets[1].data = [3, 3, 3, 3, 3, 3, 3];
            chart.update()
            break;
        case '2':
            chart.data.datasets[0].label = "Pan-40";
            chart.data.datasets[0].data = pan_40_dataset;
            chart.data.datasets[1].data = [2, 2, 2, 2, 2, 2, 2];
            chart.update()
            break;
        case '3':
            chart.data.datasets[0].label = "Evion-600";
            chart.data.datasets[0].data = envion_600_dataset;
            chart.data.datasets[1].data = [2, 2, 2, 2, 2, 2, 2];
            chart.update()
            break;
    }
}

/* 
Chart configurations are defined below
*/


// Chart configurations for "Health Report" chart
Chart.defaults.global.defaultFontColor = 'white';
// fetches the "drawing context" associated with the "health-report-chart" canvas element. Here, "2d" specifies that a two-dimensional rendering context will be created.
let ctx = document.getElementById('health-report-chart').getContext('2d');
let chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // mock data
    data: {
        labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday'
        ],
        datasets: [{
                label: 'Health Status',
                borderColor: '#f5365c',
                data: [1, 1, 1, 2, 1, 1, 2]
            }
        ]
    },

    // Optional configurations go here
    options: {
        tooltips: {
            enabled: false,
            mode: 'index',
            intersect: false,
            custom: function (model) {

                // Get tooltip
                let $tooltip = $('#chart-tooltip');

                // Create tooltip on first render
                if (!$tooltip.length) {
                    $tooltip = $(
                        '<div id="chart-tooltip" class="popover bs-popover-top" role="tooltip"></div>'
                        );

                    // Append to body
                    $('body').append($tooltip);
                }

                // Hide if no tooltip
                if (model.opacity === 0) {
                    $tooltip.css('display', 'none');
                    return;
                }

                function getBody(bodyItem) {
                    return bodyItem.lines;
                }

                // Fill with content
                if (model.body) {
                    let titleLines = model.title || [];
                    let bodyLines = model.body.map(getBody);
                    let html = '';

                    // Add arrow
                    html += '<div class="arrow"></div>';

                    // Add header
                    titleLines.forEach(function (title) {
                        html +=
                            '<h3 class="popover-header text-center">' +
                            title + '</h3>';
                    });

                    // Add body
                    bodyLines.forEach(function (body, i) {
                        let colors = model
                            .labelColors[i];
                        let styles =
                            'background-color: ' +
                            colors.backgroundColor;
                        let indicator =
                            '<span class="badge badge-dot"><i class="bg-primary"></i></span>';
                        let align = (bodyLines
                                .length > 1) ?
                            'justify-content-left' :
                            'justify-content-center';
                        html +=
                            '<div class="popover-body d-flex align-items-center ' +
                            align + '">' +
                            indicator + body +
                            '</div>';
                    });

                    $tooltip.html(html);
                }

                // Get tooltip position
                let $canvas = $(this._chart.canvas);

                let canvasWidth = $canvas.outerWidth();
                let canvasHeight = $canvas.outerHeight();

                let canvasTop = $canvas.offset().top;
                let canvasLeft = $canvas.offset().left;

                let tooltipWidth = $tooltip.outerWidth();
                let tooltipHeight = $tooltip.outerHeight();

                let top = canvasTop + model.caretY -
                    tooltipHeight - 16;
                let left = canvasLeft + model.caretX -
                    tooltipWidth / 2;

                // Display tooltip
                $tooltip.css({
                    'top': top + 'px',
                    'left': left + 'px',
                    'display': 'block',
                    'z-index': '100'
                });

            },
            callbacks: {
                label: function (item, data) {
                    let label = data.datasets[item
                        .datasetIndex].label || '';
                    let yLabel = item.yLabel;
                    let content = '';

                    if (data.datasets.length > 1) {
                        content +=
                            '<span class="badge badge-primary mr-auto">' +
                            label + '</span>';
                    }

                    content +=
                        '<span class="popover-body-value">' +
                        yLabel + '</span>';
                    return content;
                }
            }
        },
        responsive: true,
        legend: {
            position: 'bottom',
        },
        hover: {
            mode: 'label'
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Per Day'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Health Status'
                },
                ticks: {
                    min: 0,
                    max: 3,
                    stepSize: 1,
                    suggestedMin: 0.5,
                    suggestedMax: 5.5,
                    callback: function (label, index,
                        labels) {
                        switch (label) {
                            case 0:
                                return '';
                            case 1:
                                return 'Worse';
                            case 2:
                                return 'Indifferent';
                            case 3:
                                return 'Better';
                        }
                    }
                },
                gridLines: {
                    display: false
                }
            }]
        }
    }
});


// calling the fetch method for all Metric and Medicine objects so as to set their latest values in current_intake and current_health_metrics maps.
candid.fetchMedicineIntake();
envion_600.fetchMedicineIntake();
pan_40.fetchMedicineIntake();
bpm.fetchMetricValue();
spo2.fetchMetricValue();

// Chart configs for "Daily Intake Report" chart
// Setting up slots in the dataset for dynamic values. These values will be fetched via the DynamoDB and their chart renders will be updated in real-time 
// using chart.update() method
let candid_dataset = [3, current_intake.get("Candid"), 2, 3, 2, 3, 3];
let envion_600_dataset = [2, current_intake.get("Envion-600"), 2, 0, 1, 2, 3];
let pan_40_dataset = [3, current_intake.get("Pan-40"), 2, 0, 2, 2, 2];

let ctrx = document.getElementById("chart-orderz").getContext("2d");
let chart = new Chart(ctrx, {
// The type of chart we want to create
type: "bar",
// mock data
data: {
    labels: [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
    ],
    datasets: [{
        label: "Candid",
        backgroundColor: "#11cdef",
        borderColor: "#11cdef",
        data: candid_dataset
    },
    {
        label: "Prescribed Dosage",
        backgroundColor: "#f5365c",
        borderColor: "#f5365c",
        data: [3, 3, 3, 3, 3, 3, 3]
    }
    ]
},
options: {
    maintainAspectRatio: false,
    scales: {
    xAxes: [{
        barPercentage: 0.5,
        barThickness: 8,
        maxBarThickness: 20,
        minBarLength: 2000,
        gridLines: {
        offsetGridLines: true
        }
    }]
    }
}
});

setTimeout(() => {
    candid_dataset[1] = current_intake.get("Candid");
    pan_40_dataset[1] = current_intake.get("Pan-40");
    envion_600_dataset[1] = current_intake.get("Envion-600");
    chart.update()
    console.log("\nUPDATING CHART NOW\n")

    document.getElementById("cardioDate").innerHTML = Date().slice(0,15);
    document.getElementById("cardioTime").innerHTML = Date().slice(16,24);
    document.getElementById("cardioBPM").innerHTML = current_health_metrics.get("BPM");
    document.getElementById("cardioSPO2").innerHTML = current_health_metrics.get("SPO2");
    console.log("done");
}, 4000)