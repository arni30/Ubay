<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="description" content="Ubay">
    <meta name="keywords" content="HTML, CSS, JS, Java, ucode, unitfactory, cbl, cblworld">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ubay</title>
    <link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="resources/favicon.ico" type="image/x-icon">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/reset.css"/>
    <link rel="stylesheet" href="resources/main.css"/>
    <link rel="stylesheet" href="resources/addLot.css"/>
    <script src="resources/references.js"></script>
</head>

<body id="body">

<div class="addLot">
    <div id="window" class="shadow-large">
        <div class="page-header addLot-header">Add feedback</div>
        <form id="form" class="addLot-form" action="addFeedback" >

            <h4 id="lot" style="text-align: center">Title</h4>

            <label for="newRate">Rate seller&nbsp;</label>
            <input id="newRate" class="button" type="number" name="rate" required
                   min=".0" max="5.0" step=".1" value="5"/>

            <label for="newFeedback">Feedback&nbsp;&nbsp;</label>
            <textarea id="newFeedback" class="button" type="text" name="description" required
                      rows="4" maxlength="200" placeholder="200 symbols"></textarea>

            <div class="addLot-controls">
                <input class="button" value="Add feedback" onclick="send()">
                <div>
                    <a class="abutton" href="#" onclick="window.history.back()">back</a>
                    <a class="abutton" href="${pageContext.request.contextPath}/main">main</a>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
<script>
    function send() {
        let form = $('#form')[0];
        let formData = new FormData(form);
        // formData.append('rate', document.getElementById("newRate").value);
        // formData.append('description', document.getElementById("newFeedback").value);
        <%--formData.append('lotId', ${lot.id});--%>
        let object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        let jsonString = JSON.stringify(object);
        console.log(jsonString);

        $.ajax({
            url : 'addFeedback',
            type : 'POST',
            contentType : "application/json; charset=utf-8",
            data : jsonString,
            async: false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            processData : false,  //To avoid making query String instead of JSON
            cache: false, //This will force requested pages not to be cached by the browser
            success : function(resposeJsonObject) {
                alert("hi stuff worked");
                // alert(data);
            },
            error : function(err) {
                alert("nope!");
                // alert(err);
            }
        });
    }
</script>

</html>
