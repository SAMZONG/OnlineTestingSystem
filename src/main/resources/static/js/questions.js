/**
 * Created by Awad on 5/9/17.
 */


var count = 0;

$(function () {
    var inventoryDDL = $("#inventorySelect");
    var subInventoryDDl = $("#subInventorySelect");
    $.ajax({
        url: '/category/getAllCategories',
        method: 'GET',
        dataType: 'JSON',
        contentType: "application/json; charset=utf-8;",
        success: function (data) {
            inventoryDDL.append($('<option></option>').val(-1).html('Select Category'));
            subInventoryDDl.append($('<option></option>').val(-1).html('Select SubCategory'));
            subInventoryDDl.prop('disabled',true);
            $.each(data, function (index, item) {
                inventoryDDL.append(
                    $('<option></option>').val(item.categoryId).html(item.categoryName));
            });
        },
        error: function (x, status, error) {
            alert(error);
        }
    })

    inventoryDDL.change(function () {
        if ($(this).val() == "-1") {
            subInventoryDDl.empty();
            subInventoryDDl.append($('<option></option>').val(-1).html('Select SubCategory'));
            subInventoryDDl.val(-1)
            subInventoryDDl.prop('disabled', true);
        }
        else
        {
            $.ajax({
                url: '/category/getSubCategories/'+$(this).val(),
                method: 'GET',
                dataType: 'JSON',
                contentType: "application/json; charset=utf-8;",
                success: function (data) {
                    subInventoryDDl.empty();
                    subInventoryDDl.append($('<option></option>').val(-1).html('Select SubCategory'));
                    subInventoryDDl.prop('disabled', false);
                    $.each(data, function (index, item) {
                        subInventoryDDl.append(
                            $('<option></option>').val(item.subCategoryId).html(item.subCategoryName));
                    });
                },
                error: function (x, status, error) {
                    alert(error);
                }
            })

        }
    });


    $("#btnAddAnswer").click(function () {


        if ($("#AnswerText").val() != "") {

            if (count < 1)
            {
                var strhtmlhead = '<tr class="head">';
                strhtmlhead += '<th>Answer</th>';
                strhtmlhead += '<th>Correct Answer</th>';
                strhtmlhead += '<th>Control</th></tr>';
                count = count + 1;
                $("#tblHeader").append(strhtmlhead);

            }
            var strHtml = '';

            strHtml += '<tr class="answer">';
            strHtml += '<td style="display: none" class="answer-id"></td>';
            strHtml += '<td class="answer-text">' + $("#AnswerText").val() + '</td>';

            strHtml += '<td class="correct-answer">';
            strHtml += '<input type="radio" name="correctAnswerRadio" value=' + $("#AnswerText").val() +' ></td>'

            strHtml += '<td class="answer-delete">';
            strHtml += '<input type="button " id="btnDeleteAnswer" class="btn btn-danger delete-answer" value="Delete Answer" />';
            strHtml += '</td>';
            strHtml += '</tr>';

            $("#tblAnswers").append(strHtml);

            $("#AnswerText").val("");

            $(".delete-answer").click(function () {
                $(this).parent().parent().remove();
            });


        }
        else {

        }
    });



    $("#btnSave").click(function() {
        if ($("#QuestionText").val() != "" && $("#subInventorySelect").val() > 0) {

            var question = {};
            var answers = [];

            question.QuestionText = $("#QuestionText").val();
            question.subCategoryId = $("#subInventorySelect").val();


            //to find the correct answer
            var radioButtons = $("#tblAnswers input:radio[name='correctAnswerRadio']");
            var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

            if ($('.answer').length==0 || selectedIndex <0) {

                alert("You need to have a correct answer for the questions");
                return;
            } else {
                $('.answer').each(function () {
                    var answer = {};
                    answer.AnswerText = $(this).find('.answer-text').text();

                    answers.push(answer);
                });
            }

            //TODO: Make the Question Answers dynamic not only for 5

            var question = {
                question_description:question.QuestionText,
                answer_1:answers[0].AnswerText,
                answer_2:answers[1].AnswerText,
                answer_3:answers[2].AnswerText,
                answer_4:answers[3].AnswerText,
                answer_5:answers[4].AnswerText,
                subCategoryId:question.subCategoryId,
                correct_answer:selectedIndex+1

            }

            var questionObj = JSON.stringify(question);

            $.ajax({
                url: '/question/postQuestion',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: questionObj,

                success: function () {
                    alert("Question Added Successfully ");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("Status: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            });

        } else {
            alert("Please Add Question Text");
        }
    });

});
