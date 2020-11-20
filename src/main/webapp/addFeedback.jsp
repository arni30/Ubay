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
</head>

<body id="body">

<div class="addLot">
    <div id="window" class="shadow-large">
        <div class="page-header addLot-header">Add feedback</div>
        <form class="addLot-form" action="addFeedback" method="POST" name="form">

            <h4 id="lot" style="text-align: center">Title</h4>

            <label for="newRate">Rate seller&nbsp;</label>
            <input id="newRate" class="button" type="number" name="newRate" required
                   min=".0" max="5.0" step=".1" value="5"/>

            <label for="newFeedback">Feedback&nbsp;&nbsp;</label>
            <textarea id="newFeedback" class="button" type="text" name="newFeedback" required
                      rows="4" maxlength="200" placeholder="200 symbols"></textarea>

            <div class="addLot-controls">
                <input class="button" type="submit" value="Add feedback">
                <div>
                    <a class="abutton"href="#" onclick="window.history.back()">back</a>
                    <a class="abutton"href="http://localhost:8080/ubay/main">main</a>
                </div>
            </div>
        </form>
    </div>
</div>

</body>

</html>
