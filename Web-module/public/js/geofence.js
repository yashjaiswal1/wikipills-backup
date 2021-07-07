/*
This file is an extension of child/index.html file.
It implements the logic for multi-spot geofencing using the point-in-polygon (ray-casting) algorithm.
*/

let map,
marker,
isPointInside,
coordinates,
track,
counter = 0,
predefined_geofence1,
predefined_geofence2,
log_entry,
i = -1, j = 0, k, l = 1,
active_geofences = [],                                                  // stores all polygons (geofences) that are currently active
notificationRef = firebase.database().ref().child("Notifications");     // references the "Notifications" field in Firebase that is responsible 
                                                                        // for issuing notification alerts on the android app


/**
 * 
 * @function pointInPolygon
 * @param {2Darray} polygon - Stores the coordinates of all vertices of the polygon
 * @param {1Darray} point - Stores the coordinates of the point to be checked
 * @returns {bool} - Returns true if point lies inside polygon, else false
 * 
 * This is an implementation of the point-in-polygon (ray casting) algorithm.
 * An infinite ray is projected horizontally and the number of intersections with the polygon edges are counted.
 * It works on the even-odd intersection principle wherein, if there are an odd number of intersections then point is in polygon.
 * Else, we conclude that point lies outside polygon
 * 
 */
 function pointInPolygon(polygon, point){
    let n = polygon.length;                 // number of vertices in the polygon
    let intersections = 0;
    
    // X and Y coordinates of the point to be tested
    let x = point[0];
    let y = point[1];

    // pick an edge of the polygon for each iteration
    for(let i=0; i < n-1; i++){

        // assuming that the edge is composed of two points A and B
        let edge = {
            point_a: {
                x: polygon[i].lat(),
                y: polygon[i].lng()
            },
            point_b: {
                x: polygon[i+1].lat(),
                y: polygon[i+1].lng()
            }
        }

        // X and Y coordinates of point A of the current edge
        let x1 = edge.point_a.x,
            y1 = edge.point_b.y,
        
        // X and Y coordinates of point B of the current edge
            x2 = edge.point_b.x,
            y2 = edge.point_b.y;
        
        // (i) check if the point lies between A and B in terms of y-axis
        // (ii) check if the point is less than the intersection point in terms of x-axis
        if (( y < y1 != y < y2 ) && ( x < (x2-x1) * (y-y1) / (y2 - y1) + x1 )) {
            intersections += 1;
        }
    }

    // even-odd principle
    return count % 2 == 0 ? false : true;
}

/**
 * 
 * @function initMap
 * This function is invoked when the GMaps API is called. It is provided as a callback parameter.
 * It initializes various components of the map like heatmap-layer, map object, marker object, etc.
 * It also contains an event listener which listens for "overlaycomplete" events.
 * 
 */
function initMap() {

    // hard-coded coordinates to populate the heatmap
    let heatmapData = [
        {location: new google.maps.LatLng(13.116017, 77.634925), weight: 10},
        {location: new google.maps.LatLng(13.116396, 77.634861), weight: 10},
        {location: new google.maps.LatLng(13.116031, 77.635213), weight: 10},
        {location: new google.maps.LatLng(13.115893, 77.635558), weight: 10},
        {location: new google.maps.LatLng(13.116465, 77.635511), weight: 6},
        {location: new google.maps.LatLng(13.116799, 77.635470), weight: 7},
        {location: new google.maps.LatLng(13.116799, 77.635470), weight: 7},
        {location: new google.maps.LatLng(13.116830, 77.635195), weight: 8},
        {location: new google.maps.LatLng(13.117243, 77.634857), weight: 10},
        {location: new google.maps.LatLng(13.116428, 77.635216), weight: 5},
        {location: new google.maps.LatLng(13.117253, 77.634460), weight: 2},
        {location: new google.maps.LatLng(13.116365, 77.635517), weight: 10},
        {location: new google.maps.LatLng(13.116406, 77.635174), weight: 9},
        {location: new google.maps.LatLng(13.116855, 77.634836), weight: 10},
        {location: new google.maps.LatLng(13.117264, 77.635474), weight: 5},
        {location: new google.maps.LatLng(13.117489, 77.634101), weight: 3},
        {location: new google.maps.LatLng(13.117279, 77.634074), weight: 1},
        {location: new google.maps.LatLng(13.117644, 77.633806), weight: 1},
        {location: new google.maps.LatLng(13.117728, 77.633484), weight: 1},
        {location: new google.maps.LatLng(13.118088, 77.633280), weight: 10},
        {location: new google.maps.LatLng(13.117895, 77.633028), weight: 10},
        {location: new google.maps.LatLng(13.117676, 77.633178), weight: 10},
        {location: new google.maps.LatLng(13.117451, 77.632991), weight: 10}
    ];


    let heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData,
        dissapating: false,
        radius: 40,
        maxIntensity: 10
    });
    heatmap.setMap(map);


    // initializing map
    // center indicates the coordinates wherein the map view will be centered
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 13.115893, lng: 77.635558 },
        zoom: 17,
    });


    // initializing marker
    marker = new google.maps.Marker({
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP,
        position: { lat: 13.117233, lng: 77.63485 },
    });

    let markerLat = marker.position.lat();
    let markerLng = marker.position.lng();
    coordinates = [
        {
        lat: markerLat,
        lng: markerLng,
        },
    ];

    point_coordinates = [markerLat, markerLng]   // X-Y coordinates of the point to be tested


    // Draws a polyline for the path followed by the marker
    track = new google.maps.Polyline({
        path: coordinates,
        geodesic: true,
        strokeColor: "#6270E4",
        strokeOpacity: 1,
        strokeWeight: 5,
    });
    track.setMap(map);


    // Enables the drawing manager tools for Google Maps
    // Here, we enable the "polygon" drawing tool
    let drawingManager = new google.maps.drawing.DrawingManager({
        drawingMode: google.maps.drawing.OverlayType.MARKER,
        drawingControl: true,
        drawingControlOptions: {
        position: google.maps.ControlPosition.TOP_CENTER,
        drawingModes: ["polygon"],
        },
    });
    drawingManager.setMap(map);
    

    predefined_geofence1 = new google.maps.Polygon({
            paths: [
            {lat: 13.117296, lng: 77.634709},
            {lat: 13.115892, lng: 77.634812},
            {lat: 13.115892, lng: 77.635609},
            {lat: 13.117325, lng: 77.635462},
            ]
    });
    predefined_geofence1.setMap(map);
    i++;    
    active_geofences[i] = predefined_geofence1;  // remove

    predefined_geofence2 = new google.maps.Polygon({
            paths: [
            {lat: 13.118348, lng: 77.632880},
            {lat: 13.118193, lng: 77.633482},
            {lat: 13.117316, lng: 77.633350},
            {lat: 13.117175, lng: 77.632771},
            ]
    });
    predefined_geofence2.setMap(map);
    i++;
    active_geofences[i] = predefined_geofence2;



    // Whenever a polygon drawing is completed, the "overlaycomplete" event is triggered
    // This event listener actively listens to this event
    google.maps.event.addListener(
        drawingManager,
        "overlaycomplete",
        /**
         * @param {2Darray} polygon - consists of X-Y coordinates of all vertices of the polygon that has been created
         * This function fetches the @param polygon and passes it into @function pointInPolygon alongside the point coordinates.
         * The "Notifications" field in firebase is set to 1 if point is outside polygon (this rings up an alert on the Android app)
         * The "Notifications" field is set to 0 if point is inside polygon
         */
        function (polygon) {

            let notificationRef = firebase.database().ref().child("Notifications");
            let polygon_coordinates = polygon.overlay.getPath().getArray();
            
            i += 1;
            active_geofences[i] = polygon_coordinates;
            polygon_coordinates = active_geofences[j];
            isPointInside = pointInPolygon(polygon_coordinates, point_coordinates)
            
            if (isPointInside) {
                console.log("inside region 1");
                notificationRef.set(0);        // sets up the notifications field value as false on firebase
            } else {
                console.log("outside region 1");
                notificationRef.set(1);        // sets up the notifications field value as true on firebase
                j += 1;
                polygon_coordinates = active_geofences[j];
            }

            // registers the barometer reading with the created geofence
            M.toast({html: '<span>Register Altitude with Region 1?</span><button class="btn-flat toast-action" onclick="baroLog1()">Yes</button>', classes: 'rounded purple', displayLength: '10000'});
        }
    );
}


function changeMarkerPosition(marker, polygon_coordinates, track) {

        let notificationRef = firebase.database().ref().child("Notifications");
        let predefined_marker_positions = [
            [13.117225, 77.633982],
            [13.117646, 77.633977],
            [13.117780, 77.633110],
            [13.117635, 77.634084],
            [13.118328, 77.633904],
            [13.119552, 77.632891]
        ]

        // set position of the marker to the new coordinates
        new_point_coordinates = predefined_marker_positions[counter]
        marker.setPosition(new_point_coordinates);
        
        // set new coordinates for polyline
        let path = track.getPath();
        path.push(new_point_coordinates);

        isPointInside = pointInPolygon(polygon_coordinates, new_point_coordinates);
        counter += 1;
        
        if (isPointInside) {
            k = j+1;
            M.toast({html: 'Inside Region ' + k, classes: 'rounded green'});
            log_entry = document.createElement("TD");
            log_entry.innerHTML = "Entered region " + k + " at " + new Date();
            notificationRef.set(0);
        } else {
            k = j+1;
            M.toast({html: 'Outside Region ' + k, classes: 'rounded red'});
            log_entry = document.createElement("TD");
            log_entry.innerHTML = "Departed from region " + k + " at " + new Date();
            notificationRef.set(1);
            j += 1;
            polygon_coordinates = active_geofences[j];
        }

        // appends the created <td> element under history log
        document.getElementById("tr" + l).appendChild(log_entry);
        l += 1;
}
