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
        error: function (){
            alert("Failed");
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
                            ,name:  'myCheckbox'
                            ,value: subCategory.subCategoryId
                            ,type:  'checkbox'
                            ,text: subCategory.subCategoryName
                        })
                    ).append(subCategory.subCategoryName).append('<br>');

            });

        },
        error: function (){
            alert("Failed");
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
                                ,name:  'myCheckbox'
                                ,value: 'myValue'
                                ,type:  'checkbox'
                                ,text: subCategory.subCategoryName
                            })
                        ).append(subCategory.subCategoryName).append('<br>');

                });

            },
            error: function (){
                alert("Failed");
            }
        });

    });


    /*var conceptName = $('#categories').find(":selected").text();
     alert(conceptName);*/

   /* $('#containerId')
        .append(
            $(document.createElement('input')).attr({
                id:    'myCheckbox'
                ,name:  'myCheckbox'
                ,value: 'myValue'
                ,type:  'checkbox'
            })
        );*/

    /*var conceptName = $('#categories').find(":selected").val();*/
    /*$('#categories').find(":selected").val()*/


});



