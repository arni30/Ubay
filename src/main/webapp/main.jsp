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
    <!-- <link rel="stylesheet" href="css/exp.css"/> -->
    <script src="resources/main.js"></script>
    <script src="resources/main_show.js"></script>
</head>

<body>

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
            <form class="input-wrap search-wrap" action="#" method="get"
                  itemprop="potentialAction" tabindex="0">
                <!--          <input itemprop="query-input" tabindex="1" id="search-input"-->
                <!--            type="search" name="q" placeholder="change search" class="input"-->
                <!--            autocomplete="off" data-ajax-src="/suggest/">-->
                <!--        <button class="login-button" onclick="gotoSignIn()">Sign in</button>-->
                <!--        <button class="login-button" onclick="gotoProfile">Profile</button>-->
                <a class="abutton" href="http://localhost:8080/ubay/signin">Sign in</a>
                <a class="abutton" href="http://localhost:8080/ubay/profile">Profile</a>
            </form>
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
        <li><a href="#">Home</a></li>
    </ul>
</nav>

<main class="page">
    <!-- <div id="main-overlay"></div> -->

    <aside class="filters">

        <!--      <div class="search">-->
        <!--        <p>Search:</p>-->
        <!--        <select class="sort-select">-->
        <!--          <option value="1">none</option>-->
        <!--          <option value="2">cheap first</option>-->
        <!--          <option value="3">expensive first</option>-->

        <!--        </select>-->
        <!--      </div>-->

        <div class="sort">
            <p>Sort:</p>
            <select class="sort-select">
                <option value="1">none</option>
                <option value="2">cheap first</option>
                <option value="3">expensive first</option>

            </select>
        </div>

        <div>
            <p>Brand:</p>
            <label class="filterbox">J'ELITE
                <input type="checkbox" class="check-brand" value="J'ELITE">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">St.Dalfout
                <input type="checkbox" class="check-brand" value="St.Dalfout">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">Chantaine
                <input type="checkbox" class="check-brand" value="Chantaine">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">Dabur
                <input type="checkbox" class="check-brand" value="Dabur">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">Bionerica
                <input type="checkbox" class="check-brand" value="Bionerica">
                <span class="checkmark"></span>
            </label>
            <label class="filterbox">Triuga
                <input type="checkbox" class="check-brand" value="Triuga">
                <span class="checkmark"></span>
            </label>
        </div>
        <!-- <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script> -->
    </aside>
    <div class="container"></div>


</main>

<div class="basket hidden">
    <div class="basket-back"></div>
    <div id="cart" class="shadow-large">
        <div class="page-header cart-header">Cart</div>
        <div class="cart-items"></div>
        <div class="cart-total">Total: <span class="cart-numTotal item_price">$36.98</span></div>
        <div class="cart-controls">
            <a href="#" class="continue">Continue shopping</a>
            <div data-location="/checkout" class="buy button">Buy</div>

        </div>
        <div class="close-icon"></div>
        <!-- <a href="javascript:;" class="cart_empty button u-pull-left">Empty Cart <i class="fa fa-trash-o"></i></a>
        <a href="javascript:;" class="button button-primary cart_checkout u-pull-right">Checkout <i class="fa fa-arrow-right"></i></a> -->

    </div>
</div>
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

</html>
