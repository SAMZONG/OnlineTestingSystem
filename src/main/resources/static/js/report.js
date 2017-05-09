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
                "defaultContent": "<button><a href='#' style='color:darkblue'>Grade Report</a></button>",
            },
            {
                "target": -1,
                "data": null,
                "defaultContent": "<button><a href='#' style='color:darkblue'>Detail Report</a></button>"
            }
        ],
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