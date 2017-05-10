/**
 * Created by manzil on 5/9/2017.
 */
$(document).ready( function () {
    var table = $('#reportTable').DataTable({
        "ajax": "/student/reports",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"data": "reportId"},
            {"data": "studentName"},
            {"data": "categoryName"},
            {"data": "coachName"},
            {"data": "score"},
            {
                "target": -1,
                "data": null,
                "defaultContent": "<button class='grade' type='button'>Grade Report</button>",
            },
            {
                "target": -1,
                "data": null,
                "defaultContent": "<button class='details' type='button'>Detail Report</button>"
            }
        ],
    });
    $('#reportTable tbody').on('click','.grade',function () {
        var data=table.row($(this).parents('tr')).data();

        window.open("http://localhost:8080/download/pdf/grade/"+data.reportId);


    });
    $('#reportTable tbody').on('click','.details',function () {
        var data=table.row($(this).parents('tr')).data();

        window.open("http://localhost:8080/download/pdf/detail/"+data.reportId);


    });

});
// $('#employeesTable tbody').on( 'click', 'button', function () {
//     var data = table.row( $(this).parents('tr') ).data();
//     alert( data["studentId"] +"'s name is: "+ data["email"] );
// });

/* $('#employeesTable tbody').on( 'click', 'button', function () {
 var data = table.row( $(this).parents('tr') ).data();
 var student = {
 // "active":"active",
 // "studentId": "599",
 // "email":"chuang.huang12@gmail.com",
 // "firstName":"manzil",
 // "lastName": "owda"
 "studentId": data["studentId"],
 "email":data["email"],
 "firstName":data["firstName"],
 "lastName":data["lastName"],
 "active":data["active"]
 };
 /!* $.ajax({
 url: '/admin/assigntest',
 type: 'POST',
 contentType: 'application/json',
 dataType: 'json',
 data: JSON.stringify(student),
 success: function(){
 table.row($(this).parents('tr') ).
 remove().
 draw();
 },
 error: function(XMLHttpRequest, textStatus, errorThrown) {
 alert("Status: " + textStatus); alert("Error: " + errorThrown);
 }
 });*!/
 table.row($(this).parents('tr')).remove().draw();
 });
 });*/