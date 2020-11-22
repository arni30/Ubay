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
    <link rel="stylesheet" href="resources/profile.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
            <div class="header-login">
                <a class="abutton" href="#" onclick="signOut()">Sign out</a>
            </div>
        </div>

        <div class="header-middle page">
            <div class="header-contact">
                <!--        <span>(044) 044 04 04</span>-->
            </div>
            <div title="internet shop" class="logo">UBAY</div>
            <div class="header-basket">
                <!--         <a>cart<span class="front-cart">(0)</span></a>-->
            </div>
        </div>
    </header>

    <nav class="breadcrumps">
        <ul class="page crumbs">
            <li><a class="abutton" href="http://localhost:8080/ubay/main">Home</a></li>
            <li><a href="">Profile</a></li>
        </ul>
    </nav>

<div class="contact-info page">
    <section class="personal-section">
        <div class="personal-section__header">
            <h3 class="personal-section__heading">Personal info</h3>
            <!---->
            <div class="personal-section__buttons">
                <input type="button" class="button" value="Change personal info" onclick="changeInfo()">
                <input type="button" class="button" value="Change password" onclick="changePassword()">
            </div>
        </div>
        <form>
            <div class="personal-data">
                <ul class="personal-data__list">
                    <li class="personal-data__item">
                        <label class="personal-data__label form__label" id="role"> Role </label>
<%--                        ${user.userRole}--%>
                    </li>
                    <li class="personal-data__item">
                        <label class="personal-data__label form__label" id="username"> Username </label>
<%--                        ${user.login}--%>
                    </li>
                    <li class="personal-data__item">
                        <label class="personal-data__label form__label" id="email"> Email </label>
<%--                        ${user.email}--%>
                    </li>
                    <li class="personal-data__item">
                        <label  class="personal-data__label form__label" id="balance"> Balance </label>
<%--                        ${user.balance}--%>
                    </li>
                </ul>
                <!---->
            </div>
        </form>
    </section>
</div>

    <div id="profile-buttons" class="page personal-section__header">
        <a class="button" href="http://localhost:8080/ubay/addLot">Add auction</a>
        <!--js: add feedback button-->
    </div>

    <main class="page">
        <!-- <div id="main-overlay"></div> -->
        <aside>
            <h3 class="personal-section__heading">Active auctions</h3>
        </aside>
        <div class="container active-lots"></div>
    </main>

    <main class="page">
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
            <p>Terms of Servise | Privacy Policy</p>
        </div>
    </div>
</footer>

</body>
<script type="text/javascript">

    function showInfo() {
        let response = ${user};
        // let response = personalInfo.info;

        console.log(response);
        let jsonString = JSON.parse(JSON.stringify(response));
        console.log(jsonString);
        let elem, p;
        elem = document.querySelectorAll('.personal-data__item');
        for (let i = 0; elem[i]; ++i) {
            p = document.createElement('p');
            p.className = 'personal-data__value';
            if (i === 0) {
                p.innerHTML = jsonString.userRole;
                if (jsonString.userRole === 'seller') {
                    sellerFeatures.addSellersFeatures(p, jsonString.rate);
                }
            } else if (i === 1) {
                p.innerHTML = jsonString.login;
            } else if (i === 2) {
                p.innerHTML = jsonString.email;
            } else {
                p.innerHTML = jsonString.balance;
            }
            elem[i].appendChild(p);
        }
    }
    showInfo();
</script>

</html>

