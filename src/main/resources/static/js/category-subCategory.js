/**
 * Created by manzil on 5/5/2017.
 */
$(function () {


    function Category(categoryId, categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    function SubCategory(subCategoryId, subCategoryName) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }

    function CategorySubCategory(category, subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }


    $.ajax({
        type: 'get',
        dataType: 'json',
        url: '/student/categories',
        success: function (data) {
            $.each(data, function (i, category) {
                $('#categories')
                    .append($("<option></option>")
                        .attr("value", category.categoryId)
                        .text(category.categoryName));
            });

        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("No SubCategories Available" + " Error:" + xhr.status);
        }
    });

    $.ajax({
        type: 'get',
        dataType: 'json',
        url: '/student/subCategories/1',
        success: function (data) {
            $.each(data, function (i, subCategory) {
                $('#subCategories')
                    .append(
                        $(document.createElement('input')).attr({
                            value: subCategory.subCategoryId
                            , type: 'checkbox'
                            , text: subCategory.subCategoryName
                            , class: 'myCheckBox'
                        })
                    ).append(subCategory.subCategoryName).append('<br>');

            });

        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("No SubCategories Available" + " Error:" + xhr.status);
        }
    });
    $("#categories").change(function () {
        $("#subCategories").empty();
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: '/student/subCategories/' + $("#categories").val(),
            success: function (data) {
                $.each(data, function (i, subCategory) {
                    $('#subCategories')
                        .append($(document.createElement('input')).attr
                            ({
                                value: subCategory.subCategoryId
                                , type: 'checkbox'
                                , text: subCategory.subCategoryName
                                , class: 'myCheckBox'
                            })
                        ).append(subCategory.subCategoryName).append('<br>');

                });

            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("No SubCategories Available" + " Error:" + xhr.status);
            }
        });

    });

    $("#examButton").click(function () {
        var subCategories = [];
        var total = $(":checkbox").length;
        var count = $(":checkbox:checked").length;
        if ((count < 3 || count > 4) && total >= 3) {
            alert("Choose either 3 or 4 SubCategories");
        }
        else {
            id = 1;
            name = "Java";
            id = parseInt($("#categories").val());
            name = $("#categories option:selected").text();
            category = new Category(id, name);

            $(":checkbox:checked").each(function () {
                sid = parseInt($(this).attr("value"));
                sname = $(this).attr("text");
                subCategory = new SubCategory(sid, sname);
                subCategories.push(subCategory);
            })
            categorySubCategory = new CategorySubCategory(category, subCategories);
            var json = JSON.stringify(categorySubCategory);
            console.log(json);
            passSelectedValues(json);
        }
    });

    function passSelectedValues(json) {

        $.ajax({
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            url: '/student/exam',
            data: json,
            success: function (data) {
                alert("Data is posted");
                console.log(data);

            },
            error: function (data, xhr, ajaxOptions, thrownError) {
                alert("Failed");

            }
        });

    }

});



