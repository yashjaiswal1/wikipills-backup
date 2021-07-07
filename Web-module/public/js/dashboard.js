/*
This javascript file consists of chart configuration details and logic that brings interactivity to chart.js charts present in the dashboard.html file.
Following is the functionality offered by this file:

                    -> Describes the switch-case logic for swapping between different mock datasets and updating the view of the chart. This event occurs whenever
                       the user clicks on a field value to select an epidemic name or may occur if they select a specific age group in the pie chart.
                       Example: displaying different data values for different epidemics selected from the field in the "EPIDEMIC Compare" chart.
                       
                    -> Describes the config for every chart. Sets up the global requirements like chart-type, and datasets as well as optional requirements.
*/



/*
Dataset swap functions defined below
*/

// Function defined for use with "Compare Epidemic" chart
function getEpidemicData(choice) {
    switch (choice) {
    case '1':
        epi_compare_chart_instance.data.datasets[0].label = "Affected";
        epi_compare_chart_instance.data.datasets[0].data = [65, 30, 20, 35, 10, 45, 45, 50];
        epi_compare_chart_instance.update()
        break;
    case '2':
        epi_compare_chart_instance.data.datasets[0].label = "Affected";
        epi_compare_chart_instance.data.datasets[0].data = [ 48, 27, 36, 47, 28, 30, 53, 75];
        epi_compare_chart_instance.update()
        break;
    case '3':
        epi_compare_chart_instance.data.datasets[0].label = "Affected";
        epi_compare_chart_instance.data.datasets[0].data = [15, 30, 20, 45, 10, 45, 55, 60];
        epi_compare_chart_instance.update()
        break;
    case '4':
        epi_compare_chart_instance.data.datasets[0].label = "Affected";
        epi_compare_chart_instance.data.datasets[0].data = [55, 40, 25, 30, 40, 35, 65, 50];
        epi_compare_chart_instance.update()
        break;
    case '5':
        epi_compare_chart_instance.data.datasets[0].label = "Affected";
        epi_compare_chart_instance.data.datasets[0].data = [65, 50, 40, 45, 30, 15, 25, 20];
        epi_compare_chart_instance.update()
        break;
    }
}

// Function defined for use with "Targeted Age Groups" pie-chart
function getAgeData(choice) {
    switch (choice) {
        case '1':
            age_piechart.data.datasets[0].data = [10, 50, 25, 15];
            age_piechart.update();
            break;
        case '2':
            age_piechart.data.datasets[0].data = [55, 25, 15, 5];
            age_piechart.update();
            break;
        case '3':
            age_piechart.data.datasets[0].data = [0, 50, 25, 25];
            age_piechart.update();
            break;
        case '4':
            age_piechart.data.datasets[0].data = [15, 45, 35, 5];
            age_piechart.update();
            break;
        case '5':
            age_piechart.data.datasets[0].data = [3, 50, 25, 22];
            age_piechart.update();
            break;
        }
}





/*
Chart Configurations defined below
*/

// Chart configurations for "Cardiovascular Health Metrics" chart starts here...
// fetches the "drawing context" associated with the "cardio-metrics-chart" canvas element. Here, "2d" specifies that a two-dimensional rendering context will be created.
let ctx = document.getElementById("cardio-metrics-chart").getContext("2d");                

// chart declaration
let cardio_chart_instance = new Chart(ctx, 
    {

    // The type of chart we want to create
    type: "line",

    data: {
        // X-axis labels
        labels: [
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "saturday",
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday"
        ],
        
        // Mock dataset is being used here to represent different BPM levels
        datasets: [{
            label: "Tachycardia rate",
            borderColor: "#f0ff00",
            data: [100, 100,100, 100, 100, 100, 100, 100, 100, 100, 100, 100]
            },
            {
            label: "User",
            borderColor: "#11cdef",
            data: [80, 86, 75, 72, 81, 84, 85, 89, 94, 98, 97, 95]
            },
            {
            label: "Bradycardia rate",
            borderColor: " #ff0000",
            data: [60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60]
            }
            
        ]
    },

    // Configuration options go here
    options: {
    scales: {
            yAxes: [{
            ticks: {
                min: 40,
                max: 120
                }
            }]
        },
    legend: {
        display: true,
        labels: {
        fontColor: "#f4f5f7",
        },
    },
    tooltips: {
        enabled: false,
        mode: "index",
        intersect: false,
        custom: function (model) {
        // Get tooltip
        let $tooltip = $("#chart-tooltip");

        // Create tooltip on first render
        if (!$tooltip.length) {
            $tooltip = $(
            '<div id="chart-tooltip" class="popover bs-popover-top" role="tooltip"></div>'
            );

            // Append to body
            $("body").append($tooltip);
        }

        // Hide if no tooltip
        if (model.opacity === 0) {
            $tooltip.css("display", "none");
            return;
        }

        function getBody(bodyItem) {
            return bodyItem.lines;
        }

        // Fill with content
        if (model.body) {
            let titleLines = model.title || [];
            let bodyLines = model.body.map(getBody);
            let html = "";

            // Add arrow
            html += '<div class="arrow"></div>';

            // Add header
            titleLines.forEach(function (title) {
            html +=
                '<h3 class="popover-header text-center">' +
                title +
                "</h3>";
            });

            // Add body
            bodyLines.forEach(function (body, i) {
            let colors = model.labelColors[i];
            let styles =
                "background-color: " + colors.backgroundColor;
            let indicator =
                '<span class="badge badge-dot"><i class="bg-primary"></i></span>';
            let align =
                bodyLines.length > 1 ?
                "justify-content-left" :
                "justify-content-center";
            html +=
                '<div class="popover-body d-flex align-items-center ' +
                align +
                '">' +
                indicator +
                body +
                "</div>";
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

        let top =
            canvasTop + model.caretY - tooltipHeight - 16;
        let left =
            canvasLeft + model.caretX - tooltipWidth / 2;

        // Display tooltip
        $tooltip.css({
            top: top + "px",
            left: left + "px",
            display: "block",
            "z-index": "100"
        });
        },
        callbacks: {
        label: function (item, data) {
            let label =
            data.datasets[item.datasetIndex].label || "";
            let yLabel = item.yLabel;
            let content = "";

            if (data.datasets.length > 1) {
            content +=
                '<span class="badge badge-primary mr-auto">' +
                label +
                "</span>";
            }

            content +=
            '<span class="popover-body-value">' +
            yLabel +
            "</span>";
            return content;
        }
        }
    }
    }

    }
);



// Chart configurations for "Compare Epidemic" chart starts here...
let ctrx = document.getElementById("epidemic-compare-chart").getContext("2d");
let epi_compare_chart_instance = new Chart(ctrx, { 
    // The type of chart we want to create
    type: "bar",
    data: {
    labels: [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August"
    ],

    // mock dataset
    datasets: [{
        label: "Affected",
        backgroundColor: "#f5365c",
        borderColor: "#f5365c",
        
        data: [65, 30, 20, 35, 10, 45, 45, 50]
        },
        {
        label: "Recovered",
        backgroundColor: "#172b4d",
        borderColor: "#172b4d",
        data: [35, 30, 20, 25, 20, 25, 45, 65]
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




// Pie chart configurations for displaying "Targeted Age Groups" affected by epidemics
let canvas = document.getElementById("affected-age-piechart");
let ctx = canvas.getContext("2d");

// Global Options:
let data = {
    labels: ["<18", "18-35", "35-60", "60+"],
    datasets: [{
        fill: true,
        backgroundColor: [
        "#80b3ff",
        "#3385ff",
        "#005ce6",
        "#003d99"
        ],
        data: [10, 50, 25, 15],
        borderColor: ["white", "white"],
        borderWidth: [2, 2]
    }]
};

let options = {
    rotation: -0.7 * Math.PI
};

// Chart declaration:
let age_piechart = new Chart(ctx, {
    type: "pie",
    data: data,
    options: options
});



// Chart configurations for "Region-wise Epidemic Spread"
let cannvas = document.getElementById("region-epidemic-barchart");
let cttx = cannvas.getContext("2d");
let BChart = new Chart(cttx, {
type: 'bar',
data: {
    labels: [
    "Karnataka",
    "Rajasthan",
    "Jharkhand",
    "Uttar Pradesh",
    "Madhya Pradesh",
    "Gujrat",
    "Assam",
    "Sikkim",
    "Punjab",
    "Tamil Nadu",
    "Goa",
    "West Bengal"
    ],

    // Mock data
    datasets: [{
    label: "Dengue",
    backgroundColor: "#fdad9b",
    borderColor: "white",
    data: [90, 50, 25, 35, 45, 75, 12, 23, 54, 21, 43, 54]
    }, {
    label: "Malaria",
    backgroundColor: "#fc8469",
    borderColor: "white",
    data: [15, 21, 4, 32, 12, 43, 78, 43, 87, 43, 75, 34]
    }, {
    label: "Common Cold",
    backgroundColor: "#fb6340",
    borderColor: "white",
    data: [45, 21, 43, 6, 65, 86, 23, 54, 76, 37, 94, 97]
    }, {
    label: "Chicken Pox",
    backgroundColor: "#fa461e",
    borderColor: "white",
    data: [88, 23, 54, 6, 46, 86, 62, 35, 14, 16, 34, 27]
    }, {
    label: "Diabetes",
    backgroundColor: "#e12d05",
    borderColor: "white",
    data: [25, 42, 74, 65, 35, 19, 45, 57, 87, 90, 81, 76]
    }, {
    label: "Asthama",
    backgroundColor: "#af2304",
    borderColor: "white",
    data: [12, 43, 64, 44, 23, 65, 25, 34, 55, 23, 45, 56]
    }, {
    label: "Bronchitis",
    backgroundColor: "#7d1903",
    borderColor: "white",
    data: [12, 43, 54, 65, 87, 98, 34, 65, 43, 76, 8, 45]
    }, {
    label: "Others",
    backgroundColor: "#4b0f02",
    borderColor: "white",
    data: [32, 34, 75, 34, 65, 34, 76, 9, 45, 34, 57, 67]
    }]
}
});
