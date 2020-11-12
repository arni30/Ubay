<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="ubay">
    <meta name="keywords" content="HTML, CSS, JavaScript, java, ucode, unitfactory, cbl, cblworld, ubay">
    <%--    <meta name="author" content="Tetiana Rohalska">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Exception</title>
    <link rel="shortcut icon" href="http://localhost:8080/ubay/favicon.ico" type="image/x-icon"/>
    <link rel="icon" href="http://localhost:8080/ubay/favicon.ico" type="image/x-icon"/>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
<body style="font-family: 'Montserrat', sans-serif">
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
</body>
</html>
