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
    <script src="resources/addLot.js"></script>
</head>

<body id="body">

<div class="addLot">
    <div id="window" class="shadow-large">
        <div class="page-header addLot-header">Add lot</div>
        <form class="addLot-form" action="addLot" method="POST" name="form" enctype="multipart/form-data">

            <label for="photo">Photo</label>
            <input id="photo" class="button" type="file" name="photo" value="photo" required/>

            <label for="title">Title</label>
            <input id="title" class="button" type="text" name="title" required/>

            <label for="category">Category</label>
            <select id="category" class="button" name="category">
                <option>technics</option>
                <option>furniture</option>
                <option>books</option>
                <option>clothes</option>
                <option>food</option>
                <option>others</option>
            </select>

            <label for="startPrice">Start price</label>
            <input id="startPrice" class="button" type="number" name="startPrice" required
                   min=".01" step=".01" max="10000"/>

            <label for="bidStep">Bid step</label>
            <input id="bidStep" class="button" type="number" name="bidStep" required
                   min=".01" step=".01" max="1000"/>

            <label for="description">Description</label>
            <textarea id="description" class="button" type="text" name="description"
                      rows="4" maxlength="200" placeholder="200 symbols"></textarea>

            <label for="duration">Duration - <span id="durationNumber">1 day</span></label>
            <input id="duration" type="range" name="duration" min="1" max="7" value="1"/>

            <div class="addLot-controls">
                <input class="button" type="submit" value="Add lot">
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
    <%--function showInfo() {--%>
    <%--    let response = ${user};--%>

    <%--    console.log(response);--%>
    <%--    let jsonString = JSON.parse(JSON.stringify(response));--%>
    <%--    --%>
    <%--}--%>
</script>

</html>

