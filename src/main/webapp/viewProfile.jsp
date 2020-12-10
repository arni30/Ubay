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
    <link rel="stylesheet" href="resources/profile.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="resources/references.js"></script>
    <script src="resources/profile.js"></script>
    <script src="resources/main_show.js"></script>
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
            <li><a class="abutton" href="${pageContext.request.contextPath}/main">Home</a></li>
            <li><a>View Profile</a></li>
        </ul>
    </nav>

    <div class="contact-info page">
        <section class="personal-section">
            <div class="personal-section__header">
                <h3 class="personal-section__heading">Personal info</h3>
            </div>
            <form>
                <div class="personal-data">
                    <ul class="personal-data__list">
                        <li class="personal-data__item">
                            <label class="personal-data__label form__label" id="role"> Role </label>

                        </li>
                        <li class="personal-data__item">
                            <label class="personal-data__label form__label" id="aboutProfile"> Login </label>

                        </li>
                        <li class="personal-data__item">
                            <label class="personal-data__label form__label" id="email"> Email </label>

                        </li>
                        <li class="personal-data__item">
                            <label  class="personal-data__label form__label" id="balance"> Balance </label>

                        </li>
                    </ul>
                </div>
            </form>
        </section>
    </div>

    <div id="profile-buttons" class="page personal-section__header">
        <a id="biddersFeedbacks" class="button" href="#">Bidders feedbacks</a>
        <a id="addLotId" href="#" style="display: none"></a>
    </div>

    <main class="page" id="activeBox" style="display: none">
        <aside>
            <h3 class="personal-section__heading">Active auctions</h3>
        </aside>
        <div class="container active-lots"></div>
    </main>

    <main class="page" id="closedBox" style="display: none">
        <aside>
            <h3 class="personal-section__heading">Closed auctions</h3>
        </aside>
        <div class="container closed-lots"></div>
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
    if (${user}) {
        personalInfo.info = ${user};
    }
    if (${lots}) {
        lots.items = ${lots};
    }
    let authorizedLogin = `<%= authorizedLogin%>`;
    if (authorizedLogin !== 'null') {
        setAuthorizedUser(authorizedLogin);
    }
</script>

</html>

