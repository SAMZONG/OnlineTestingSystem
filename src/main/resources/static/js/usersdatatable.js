$(document).ready( function () {
    var table = $('#usersTable').DataTable({
        "ajax": "/admin/getAllUsers",
        "sAjaxDataProp":"",
        "order": [[ 0, "asc" ]],
        "aoColumns" : [
            { "data": "id"},
            { "data": "name"},
            { "data": "lastName" },
            { "data": "email" },
            {
                "target": -1,
                "data": null,
                "defaultContent": "<button>Delete</button>"
            }
        ],
    });

    $('#usersTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr') ).data();
        var user = {

            "id": data["id"],
            "name":data["name"],
            "lastName":data["lastName"],
            "email":data["email"],
            "active":data["active"]
        };

        $.ajax({
            url: '/admin/deleteUser',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(user),

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