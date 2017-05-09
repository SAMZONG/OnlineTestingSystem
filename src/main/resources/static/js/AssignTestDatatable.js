$(document).ready( function () {
    var table = $('#studentTable').DataTable({
        "ajax": "/student/getAvailableStudent",
        "sAjaxDataProp":"",
        "order": [[ 0, "asc" ]],
        "aoColumns" : [
            { "data": "studentId"},
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "email" },
            {
                "target": -1,
                "data": null,
                "defaultContent": "<button>Generate Access Code</button>"
            }
        ],
    });

    // $('#employeesTable tbody').on( 'click', 'button', function () {
    //     var data = table.row( $(this).parents('tr') ).data();
    //     alert( data["studentId"] +"'s name is: "+ data["email"] );
    // });

    $('#studentTable tbody').on( 'click', 'button', function () {
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

        $.ajax({
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
        });

         table.row($(this).parents('tr')).remove().draw();
    });

});