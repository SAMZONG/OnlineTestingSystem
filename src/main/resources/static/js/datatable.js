$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/student/getAllStudent",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "studentId"},
		          { "mData": "firstName" },
				  { "mData": "lastName" },
				  { "mData": "email" },
				  // { "mData": "active" }
			]
	 })
});