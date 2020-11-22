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
    <script src="resources/feedbacks.js"></script>
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
                <a class="abutton" href="http://localhost:8080/ubay/profile">Profile</a>
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
            <li><a href="#" onclick="gotoSeller()">Seller</a></li>
            <li><a href="#">Feedbacks</a></li>
        </ul>
    </nav>

    <h3 class="page feedbacks-header">
        <a style="color: #494d5f" href="#" onclick="gotoSeller()"><span></span>
            <span class="seller-rating seller-rating fa fa-fw fa-star field-icon"></span><span id="rate" class="seller-rating"></span>
        </a>
    </h3>

    <div class="page feedbacks-container"></div>

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

</script>

</html>
