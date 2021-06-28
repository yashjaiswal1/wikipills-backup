// /*var submitBtn = document.getElementById("submitBtn");
// var heading = document.getElementById("heading");
// function submitClick(){
//     var firebaseRef = firebase.database().ref();

//     firebaseRef.child("some key").set("some value");
    
//     firebaseRef.child("name").on('value',function(snapshot){
//         heading.innerHTML = snapshot.val();
//     });
// }*/

// // (1) Add new field in dataTable (Agents) once a user registers via the mobile app and display it in the dataTable
// var dbRefObject = firebase.database().ref().child("Agent");

// dbRefObject.on('child_added',function(snapshot){
//     var obj = snapshot.val();
//     fnClickAddRow(obj);
// });
// alert = function() {};
// function fnClickAddRow(obj) {
//     //performance = ( leads converted * lead multiplier ) + product priority
//     //performance difference = ( p(current week) - p(previous week) ) / p(current week) * 100
//     var multiplier = 1.25; 
//     var priority = 0;
//     var performance = (Number(obj.leadConverted) * multiplier) + priority;
//     var diff = (( (performance - 45) / performance ) * 100).toFixed(2);
//     if(performance == 0){diff = 0}
//     $('#example').dataTable().fnAddData( [
// 		obj.name,
//         obj.state,
//         obj.email,
//         obj.leadConverted,
//         obj.lead,
//         diff + "%",
//         obj.payment
//          ] );
    
// }

// // (1.5) Real-time updation of payment request
// dbPayment = firebase.database().ref().child('Agent');

// dbPayment.on("child_changed",function(snap){
//     console.log(snap.val().payment);
// });

// // (1.75) Bank DB point allocation

// dbAlloc = firebase.database().ref().child('Leads');

// dbAlloc.on("child_changed",function(snap){
//     firebase.database().ref().child("Temp").set(snap.val().name);
//     pointAssign1(snap.val().name);
//     //pointAssign(snap.val());
// });

// dbTemp = firebase.database().ref().child("Temp");

// function initAlloc(){
//     dbTemp.on("value",function(snap){
//         pointAssign1(snap.val());
//         //pointAssign2(snap.val());
//         //pointAssign(snap.val());
//     });
// }

// function pointAssign1(name){

// //firebase.database().ref().child("Temp").on("value",function(snapshot){

//     console.log("T H I S -----> " + name);
//     // Condition: Lead not converted

//     if(document.getElementById("sjs-A12") == null){
//         firebase.database().ref().child("Convert").child("convert").set("0");
//     }

//     // Condition: Lead converted

//     else if(document.getElementById("sjs-A12").innerHTML == name){
//         console.log("OK");
//         firebase.database().ref().child("Convert").child("convert").set("1");
//     }

//     // Condition: Last row filled with a different name
//     else{
//         firebase.database().ref().child("Convert").child("convert").set("0");
//     }
    
// //});
// }

// //Reminder: nested child: convert inside Convert
// function pointAssign2(name){
// //firebase.database().ref().child("Temp").on("value",function(snapshot){

//     console.log("T H I S 2-----> " + name);
//     // Condition: Lead not converted

//     if(document.getElementById("sjs-A13") == null){
//         console.log("KO");
//         firebase.database().ref().child("Convert").set("0");
//     }

//     // Condition: Lead converted

//     else if(document.getElementById("sjs-A13").innerHTML == name){
//         firebase.database().ref().child("Convert").set("1");
//     }

//     // Condition: Last row filled with a different name

//     else{
//         firebase.database().ref().child("Convert").set("0");
//     }
// //});
// }

// // (1.98)
// firebase.database().ref().child("Leads").on('child_added',function(snapshot){
//     console.log("Child Addded!");
//     var emaill = snapshot.val().email;
//     //var i;
//     console.log('originial: '+emaill);
//     /*for(i=0;i<emaill.length;i++){
//         console.log("BEFORE CONDITION: "+emaill[i]);
//         if(emaill[i] == "."){
            
//             emaill[i] = "%";
            
//         }
//         console.log("AFTER CONDITION: "+emaill[i]);
//     }*/
//     //console.log(emaill);
//     var a = emaill.replace(".","%");
    
//     console.log(snapshot.val().name);
//     document.getElementById('dynamo').innerHTML = snapshot.val().name;
//     //document.getElementById("confirmation").innerHTML = 'Confirmed';    //change text to 'Converted' and add a tick icon...
//     //var leadVal = document.getElementById("leadz").value;
//     //console.log(emailz);
//     console.log(a);
//     //firebase.database().ref().child("Leads").child(String(a)).child("check").set("1");
    
// });

// function demo(){
//     document.getElementById("confirmation").innerHTML = 'Confirmed';
//     firebase.database().ref().child("Convert").child("convert").set("1");
// }

// // (1.99) POINT ALLOCATION using Accept-Reject methodology

    
// /*    switch(leadVal){
//         case '1':   id = 'abc1@xyz%com';
//                     break;
//         case '2':   id = 'abc3@xyz%com';
//                     break;
//         case '3':   id = 'abc4@xyz%com'
//                     break;
//         case '4':   id = 'abc32@xyz%com'
//                     break;
//         case '5':   id = 'abc30@xyz%com'
//                     break;
//         case '6':   id = 'abc11@xyz%com'
//                     break;
//         case '7':   id = 'abc10@xyz%com'
//                     break;
//         case '8':   id = 'abc12@xyz%com'
//                     break;
//         case '9':   id = 'abc13@xyz%com'
//                     break;
//         case '10':  id = 'abc14@xyz%com'
//                     break;
//         case '11':  id = 'abc8@xyz%com'
//                     break;
//         case '12':  id = 'abc2@xyz%com'
//                     break;
//         case '13':  id = 'abc6@xyz%com'
//                     break;
//         case '14':  id = 'abc7@xyz%com'
//                     break;
//         case '15':  id = emailz;
//                     break;
//     }*/
    
    
    






// // (2) Add new field in dataTable (Leads) upon logging the lead data from the mobile app and display it in the dataTable
// var dbRefLead = firebase.database().ref().child("Leads");

// dbRefLead.on('child_added',function(snapshot){
//     var obj = snapshot.val();
//     fnAddRow(obj);
// });
// alert = function() {};
// function fnAddRow(obj) {

//     $('#example2').dataTable().fnAddData( [
// 		obj.name,
//         obj.refer,
//         obj.age,
//         obj.email,
//         obj.product,
//         obj.date,

//          ] );
// }


// // (3) Setting priority
// dbRefProd = firebase.database().ref();


// function changePriority(){
//     dbRefProd.child("Products").child("Agricultural_Loans").child("priority").set(document.getElementById("prod3").value);
//     dbRefProd.child("Products").child("CASA").child("priority").set(document.getElementById("prod4").value);
//     dbRefProd.child("Products").child("Corporate_Finance").child("priority").set(document.getElementById("prod8").value);
//     dbRefProd.child("Products").child("Priority_Sector Loans").child("priority").set(document.getElementById("prod7").value);
//     dbRefProd.child("Products").child("Education_Loans").child("priority").set(document.getElementById("prod2").value);
//     dbRefProd.child("Products").child("FD").child("priority").set(document.getElementById("prod5").value);
//     dbRefProd.child("Products").child("Retail_Loans").child("priority").set(document.getElementById("prod1").value);
//     dbRefProd.child("Products").child("SME_Loans").child("priority").set(document.getElementById("prod6").value);

// }


// // (4) Points Conversion Rate

// function pointsConversion(){
//     dbRefProd.child("Rate").set(document.getElementById("encashment").value)
// }

// dbRefProd.child("Rate").on("value", function(snapshot){
//     document.getElementById("encashment").value = snapshot.val();
// });



// notifRef.set(1);

