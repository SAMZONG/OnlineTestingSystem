/**
 * Created by Chuang on 2017/5/9.
 */
function sendAjaxRequest() {
    var optionValue = $("#optionList").val();

    $.get("/admin/select?option=" + optionValue, function (data) {
//            alert("kieu");
//            $("#kieu").empty();
//            $("#kieu").append(data);
//            data.forEach(function(item, i) {
//                var option = "<option value = " + item + ">" + item +  "</option>";
//                $("#region").append(option);
//            });


        window.location.reload();

    });
}
;

$(document).ready(
    function () {
        $("#optionList").change(function () {
            sendAjaxRequest();
        });
    }
);