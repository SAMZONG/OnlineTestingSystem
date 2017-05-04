$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"ajax": "/student/getAllStudent",
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
                    "defaultContent": "<button>Click!</button>"
				  }
			],
	 	});

    $('#employeesTable tbody').on( 'click', 'button', function () {
        var data = table.row( $(this).parents('tr') ).data();
        alert( data["studentId"] +"'s name is: "+ data["email"] );
    });
});