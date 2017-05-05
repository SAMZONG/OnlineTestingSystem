
(function() {

    var questions= new Object();
    questions.question="";
    questions.choices=new Array();
    questions.correctAnswer="";
    questions.selectedAnswer="";
    var test={};

    var timer=60;
    var min=0;
    var sec=0;

    function startTimer(){
        min=parseInt(timer/60);
        sec=parseInt(timer%60);

        if(timer<1){
              window.location.href="http://localhost:8080/test";
        }

        document.getElementById("time").innerHTML = "<b>Time Left: </b>"+min.toString()+":"+sec.toString();
        timer--;
        setTimeout(function(){ startTimer(); }, 1000);
    }


    function fire_ajax_submit() {

        var search = {}
        search["subcategory"] = $("#subcategory").val();

        $("#btn-search").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/getQuestions",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
            test=data;
                console.log("data is ", data.result[0].question);
                var json = "<h4> Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                 questions=data.result;
                 console.log("Questionss: "+questions);
                 console.log(" one "+search.subcategory+" Sub "+$('#subcategory').val());
                $('#category-id').append($('#subcategory').val());

                console.log("SUCCESS : ", data);
                $("#btn-search").prop("disabled", false);

              /*  window.location.href="http://localhost:8080/test";*/

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search").prop("disabled", false);

            }
        });

    }


    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });



  
  
  
  
  
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
       /* startTimer();*/
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
            url: "/saveResult",
            data: JSON.stringify(test),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {


            },
            error: function (e) {


            }
        });

    }


})();