
(function() {

    $('#container').hide();
    var questions= new Object();
    questions.question="";
    questions.choices=new Array();
    questions.correctAnswer="";
    questions.selectedAnswer="";
    var test={};

    var search = {};

    var timer=120;
    var min=0;
    var sec=0;

    function startTimer(){
        min=parseInt(timer/60);
        sec=parseInt(timer%60);

        if(timer<1){
              store_result();
              window.location.href="http://localhost:8080/student/test";
        }

        document.getElementById("time").innerHTML = "<b>Time Left: </b>"+min.toString()+":"+sec.toString();
        timer--;
        setTimeout(function(){ startTimer(); }, 1000);
    }


    function fire_ajax_submit(jsonObject) {


/*
        search["bkgsubcategory"] = $("#bkgsubcategory").val();*/

       /* $("#btn-search").prop("disabled", true);*/

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/student/getQuestions",
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {


                test=data;
                console.log("data is ", data.result[0].question);
                var json = "<h4> Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);
                $('.subCategoryNames').append(data.subCategoryNames);

                 questions=data.result;/*
                $('#category-id').append($('#bkgsubcategory').val());*/

                console.log("SUCCESS : ", data);/*
                $("#btn-search").prop("disabled", false);*/

              /*  window.location.href="http://localhost:8080/test";*/

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);/*
                $("#btn-search").prop("disabled", false);*/

            }
        });

    }


    /*$("#search-form").submit(function (event) {
        $('#techSelection').hide();
        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });*/



  
  
  
  
  
    var questionCounter = 0; //Tracks question number
    var selections = []; //Array containing user choices
    var quiz = $('#quiz'); //Quiz div object

    // Display initial question
    displayNext();
    // Click handler for the 'next' button
    $('#next').on('click', function (e) {
        e.preventDefault();

        // Suspend click listener during fade animation
        if(quiz.is(':animated')) {        
            return false;
        }
        choose();

        // If no user selection, progress is stopped
        if (isNaN(selections[questionCounter])) {
            alert('Please select answer for this question!');
        } else {
            questionCounter++;
            displayNext();
        }
    });

    $('#saveResult').on('click', function (e) {
        store_result();
    });
    
    // Click handler for the 'prev' button
    $('#prev').on('click', function (e) {
        e.preventDefault();

        if(quiz.is(':animated')) {
            return false;
        }
        choose();
        questionCounter--;
        displayNext();
    });

    // Click handler for the 'Start Over' button
    $('#start').on('click', function (e) {
        startTimer();
        e.preventDefault();
        
        if(quiz.is(':animated')) {
            return false;
        }
        questionCounter = 0;
        selections = [];
        displayNext();
        $('#start').hide();
        $('#saveResult').hide();


    });

    // Animates buttons on hover
    $('.button').on('mouseenter', function () {
        $(this).addClass('active');
    });
    $('.button').on('mouseleave', function () {
        $(this).removeClass('active');
    });

    // Creates and returns the div that contains the questions and 
    // the answer selections
    function createQuestionElement(index) {
        var qElement = $('<div>', {
            id: 'question'
        });

        var header = $('<h2>Question ' + (index + 1) + ':</h2>');
        qElement.append(header);

        var question = $('<p>').append(questions[index].question);
        qElement.append(question);

        var radioButtons = createRadios(index);
        qElement.append(radioButtons);

        return qElement;
    }

    // Creates a list of the answer choices as radio inputs
    function createRadios(index) {
        var radioList = $('<ul>');
        var item;
        var input = '';
        for (var i = 0; i < questions[index].choices.length; i++) {
            item = $('<li>');
            input = '<div class="radio"><label><input type="radio" name="answer" value=' + i + ' >';
            input += questions[index].choices[i].toString();
            input+='</label></div>';
            item.append(input);
            radioList.append(item);
        }
        return radioList;
    }

    // Reads the user selection and pushes the value to an array
    function choose() {
        selections[questionCounter] = +$('input[name="answer"]:checked').val();
    }

    // Displays next requested element
    function displayNext() {
        quiz.fadeOut(function() {
            $('#question').remove();

            if(questionCounter < questions.length){
                var nextQuestion = createQuestionElement(questionCounter);
                quiz.append(nextQuestion).fadeIn();
                if (!(isNaN(selections[questionCounter]))) {
                    $('input[value='+selections[questionCounter]+']').prop('checked', true);
                }

                console.log("Counter "+questionCounter);
                // Controls display of 'prev' button

                if(questionCounter === 1){
                    $('#prev').fadeIn();
                } else if(questionCounter === 0){
                    $('#saveResult').fadeOut();
                    $('#prev').fadeOut();
                    $('#next').fadeIn();
                }
            }else {
                var scoreElem = displayScore();
                quiz.append(scoreElem).fadeIn();
                $('#next').hide();
                $('#prev').hide();
                $('#saveResult').hide();

                /*
                enable this to restart the quiz
                $('#start').fadeIn();*/
                if(questionCounter>0){
                    $('#saveResult').fadeIn();
                    $('#start').fadeOut();
                }else {
                    $('#start').fadeIn();
                }

            }
        });
    }

    // Computes score and returns a paragraph element to be displayed
    function displayScore() {
        var score = $('<p>',{id: 'question'});

        var numCorrect = 0;
        for (var i = 0; i < selections.length; i++) {
            if (selections[i] === questions[i].correctAnswer) {
                numCorrect++;
            }
        }

        score.append('You got ' + numCorrect + ' questions out of ' +
            questions.length + ' right!!!');
        return score;
    }
/*Function to store the result*/
    function store_result() {

        console.log("Questions:" +questions);
        test.selectedAnswer=selections;
       /* for(var i=0;i<selections.length;i++){

            test.result[i].correctAnswer=selections[i];
        }*/
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/student/saveResult",
            data: JSON.stringify(test),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {


            },
            error: function (e) {


            }
        });
        window.location.href="http://localhost:8080/student/done";
    }
/*manzil*/


    function Category(categoryId,categoryName){
        this.categoryId=categoryId;
        this.categoryName=categoryName;
    }

    function SubCategory(subCategoryId,subCategoryName){
        this.subCategoryId=subCategoryId;
        this.subCategoryName=subCategoryName;
    }

    function CategorySubCategory(category,subCategories,accessKey){
        this.category=category;
        this.subCategories=subCategories;
        this.accessKey=accessKey;
    }







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

        var subCategories=[];
        var count= $(":checkbox:checked").length;
        if(count<3 || count>4){
            alert("Choose either 3 or 4 SubCategories");
        }
        else{

            id=1;
            name="Java";
            id=parseInt($( "#categories" ).val());
            name=$( "#categories option:selected" ).text();
            category=new Category(id,name);
            search=name;
            $(":checkbox:checked").each(function (){
                sid=parseInt($(this).attr("value"));
                sname=$(this).attr("text");
                subCategory=new SubCategory(sid,sname);
                subCategories.push(subCategory);
            })
            var accessKey= $("#accessKey").text();
            categorySubCategory=new CategorySubCategory(category,subCategories,accessKey);
           // categorySubCategory=new CategorySubCategory(category,subCategories);
            /*var jsonObject=JSON.stringify(categorySubCategory);*/
           /* console.log(json);*/
            /*passSelectedValues(jsonObject);*/
            $('#techSelection').hide();
            $('#container').show();
            fire_ajax_submit(categorySubCategory);
        }

    });

    function passSelectedValues(json){

        $.ajax({
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            url: '/student/exam',
            data: json,
            success: function (data) {
                windows
                alert("Data is posted");
                console.log(data);

            },
            error: function (data, xhr, ajaxOptions, thrownError){
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
                        alert("Failed:" + xhr.status + "ThrownError " + thrownError);

                    }
                })

            }
        });

    }


})();