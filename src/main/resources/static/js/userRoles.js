/**
 * Created by Chuang on 5/6/2017.
 */
$(function () {
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: '/admin/allRoleTypes',
        success: function (data) {
            $.each(data, function (i, roleType) {
                $('#roleTypes')
                    .append($("<option></option>")
                        .attr("value", roleType.id)
                        .text(roleType.role));
            });

        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("No RoleType Available" + " Error:" + xhr.status);
        }
    });

    $("#roleTypes").change(function () {
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: '/admin/setUserType/' + $("#roleTypes").val(),
            success: function (data) {

            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("No RoleType Available" + " Error:" + xhr.status);
            }
        });

    });
});



