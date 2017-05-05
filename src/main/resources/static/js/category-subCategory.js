/**
 * Created by manzil on 5/5/2017.
 */
$(function(){
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: '/student/categories',
        success: function (data) {
            $.each(data, function(i, category) {
                $('#categories')
                    .append($("<option></option>")
                        .attr("value",category.categoryId)
                        .text(category.categoryName));
            });

        },
        error: function (xhr, ajaxOptions, thrownError){
            alert("No SubCategories Available"+ " Error:" +xhr.status);
        }
    });

    $.ajax({
        type: 'get',
        dataType: 'json',
        url: '/student/subCategories/1',
        success: function (data) {
            $.each(data, function(i, subCategory) {
                $('#subCategories')
                    .append(
                        $(document.createElement('input')).attr({
                            id:    'myCheckbox'
                            ,value: subCategory.subCategoryId
                            ,type:  'checkbox'
                            ,text: subCategory.subCategoryName
                        })
                    ).append(subCategory.subCategoryName).append('<br>');

            });

        },
        error: function (xhr, ajaxOptions, thrownError){
            alert("No SubCategories Available"+ " Error:" +xhr.status);
        }
    });
    $("#categories").change(function () {
        $("#subCategories").empty();
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: '/student/subCategories/'+$( "#categories" ).val(),
            success: function (data) {
                $.each(data, function(i, subCategory) {
                    $('#subCategories')
                        .append($(document.createElement('input')).attr
                            ({
                                id:    'myCheckbox'
                                ,value: subCategory.subCategoryId
                                ,type:  'checkbox'
                                ,text: subCategory.subCategoryName
                            })
                        ).append(subCategory.subCategoryName).append('<br>');

                });

            },
            error: function (xhr, ajaxOptions, thrownError){
                alert("No SubCategories Available"+ " Error:" +xhr.status);
            }
        });

    });

    $("#examButton").click(function(){
        var count= $(":checkbox:checked").length;
        if(count<3 || count>4){
            alert("Choose either 3 or 4 SubCategories")
        }
        else{

        }
    })

});



