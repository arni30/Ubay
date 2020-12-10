<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String authorizedLogin = null;
    if (request.getUserPrincipal() != null) {
        authorizedLogin = request.getUserPrincipal().getName();
    }
%>

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
    <script src="resources/references.js"></script>
    <script src="resources/main_show.js"></script>
    <script src="resources/main.js"></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</head>

<body>

<div class="help_footer">
<header class="header">
    <div class="header-top-back"></div>

    <div class="header-top page">
        <div class="header-features">
        <span data-text="We deliver free of charge throughout Ukraine.">
        <span>Free delivery in Ukraine!</span>
        </span>
        </div>
        <div class="header-menu">
            <ul>
                <li><a href="#">About</a></li>
                <li><a href="#">Service</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
        <div id="header-login-first" class="header-login">
            <a class="abutton" href="${pageContext.request.contextPath}/authorization">Sign in</a>
        </div>
        <div id="header-login-second" class="header-login" style="display: none">
            <a id="authorizedLogin" class="abutton" href="${pageContext.request.contextPath}/profile"></a>
            <a class="abutton" href="${pageContext.request.contextPath}/logout">Sign out</a>
        </div>
    </div>

    <div class="header-middle page">
        <div class="header-contact"></div>
        <div title="internet shop" class="logo">UBAY</div>
        <div class="header-basket"></div>
    </div>
</header>

<nav class="breadcrumps">
    <ul class="page crumbs">
        <li><a>Home</a></li>
    </ul>
</nav>

<main class="page">
    <aside class="filters">
        <div class="sort">
            <p>Sort:</p>
            <select class="sort-select">
                <option value="1">none</option>
                <option value="2">cheap first</option>
                <option value="3">expensive first</option>
            </select>
        </div>

        <div>
            <p>Category:</p>
            <label class="filterbox">technics
                <input type="checkbox" class="check-brand" value="technics">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">furniture
                <input type="checkbox" class="check-brand" value="furniture">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">books
                <input type="checkbox" class="check-brand" value="books">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">clothes
                <input type="checkbox" class="check-brand" value="clothes">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">food
                <input type="checkbox" class="check-brand" value="food">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">others
                <input type="checkbox" class="check-brand" value="others">
                <span class="checkmark"></span>
            </label>
        </div>

        <br><br>

        <div class="search">
            <p>Search:</p>
            <form>
                <label for="searchByTitle">Title</label>
                <input id="searchByTitle" class="form-styling" type="text"
                       name="title"/>

                <label for="searchByOpenPrice">Opening price</label>
                <input id="searchByOpenPrice" class="form-styling"
                       type="number" min=".01" step=".01"
                       name="startPrice"/>

                <label for="searchBySellersRate">Sellers min rate</label>
                <input id="searchBySellersRate" class="form-styling"
                       type="number" min=".01" max="5" step=".01"
                       name="sellersRate"/>

                <label for="searchByDuration">Duration</label>
                <input id="searchByDuration" class="form-styling"
                       type="number" min="1" max="7" step="1"
                       name="duration"/>

                <label for="searchByStartTime">Start time (day)</label>
                <input id="searchByStartTime" class="form-styling" type="date"
                       name="startTime"/>
                <script>
                    document.getElementById('searchByStartTime').max = new Date().toISOString().split("T")[0];
                </script>

                <label for="searchByDescription">Description</label>
                <input id="searchByDescription" class="form-styling" type="text"
                       name="description"/>

                <input class="button" type="button" value="Search" onclick="view.submitSearch()">
            </form>
        </div>
    </aside>
    <div class="container"></div>
</main>
</div>

<footer>
    <div class="footer-back"></div>
    <div class="footflexbox page">
        <div class="copyright footbox">
            <p>Copyright &copy; 2020 | Ubay | All Rights Reserved</p>
        </div>
        <div class="terms footbox">
            <p>Terms of Service | Privacy Policy</p>
        </div>
    </div>
</footer>

</body>

<script type="text/javascript">
    if (${lots}) {
        products.items = ${lots};
    }
    let authorizedLogin = `<%= authorizedLogin%>`;
    if (authorizedLogin !== 'null') {
        setAuthorizedUser(authorizedLogin);
    }
</script>

</html>
