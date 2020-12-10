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
    <link rel="stylesheet" href="resources/auction.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="resources/references.js"></script>
    <script src="resources/auction.js"></script>
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript">
    </script>
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
            <li><a id="lotId">Lot #</a></li>
        </ul>
    </nav>

    <h3 id="title" class="page"></h3>

    <main class="page height">
        <div class="personal-section lot-img">
            <img src="" alt="image">
        </div>
        <div class="personal-section lot-description">
            <div class="personal-section__header">
                <div></div>
                <div>
                    <a style="color: #494d5f" href="#" onclick="viewProfile(this)"><span id="aboutProfile">seller</span>
                        <span class="seller-rating seller-rating fa fa-fw fa-star field-icon"></span><span id="rate" class="seller-rating"></span>
                    </a>
                    <a id="biddersFeedbacks" class="button" href="#"
                       onclick="viewFeedbacks(document.querySelector('#aboutProfile').innerHTML)">feedbacks</a>
                </div>
            </div>
            <p>Current price:&emsp;<span id="price"></span></p>
            <p>Start time:&emsp;<span id="startTime"></span></p>
            <p>Time left to closure:&emsp;<span id="timer"></span></p>
            <p id="description"></p>

            <div class="personal-section__header">
                <div>
                    <div id="newBit" style="display: none">
                        <label for="newPrice">New price </label>
                        <input id="newPrice" class="button" type="number" name="price"
                               min=".01" step=".01" max="10000" required>
                        <button id="butSubmit" type="button" class="button" onclick="send()" >Submit new bit</button>
                        <a class="button" href="#" onclick="location.reload()">Return</a>
                    </div>
                </div>
                <div id="addBit-buttons" style="display: none">
                    <a class="button" href="#" onclick="auctions.addBit()">Add bit</a>
                    <a class="button" href="#" onclick="window.history.back()">Return</a>
                </div>
            </div>

            <div class="personal-section__header" id="winner" style="display: none">
                <p>Winner: -</p>
                <p id="add-feedback-button" style="display: none">
                    <a class="button" href="#" onclick="auctions.addFeedback(this)">Add feedback</a>
                </p>
            </div>
            <p id="feedback"></p>
        </div>
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
    if (${lot}) {
        auctions.lot = ${lot};
    }
    auctions.userType = `${userType}`;
    let authorizedLogin = `<%= authorizedLogin%>`;
    if (authorizedLogin !== 'null') {
        setAuthorizedUser(authorizedLogin);
    }
    auctions.authorizedUser = authorizedLogin;
    if (!${lot.active}) {
        if (${winner})
            auctions.winner = ${winner};
    }
    async function send(){
        let newPrice = document.querySelector('#newPrice');
        if (newPrice.value < newPrice.min) {
            newPrice.value = newPrice.min;
            alert('Invalid input!');
            return;
        }
        document.querySelector('#price').innerHTML = '\$' + newPrice.value;

        let formData = new FormData();
        formData.append('price', newPrice.value);
        formData.append('lotId', ${lot.id});
        let object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        let jsonString = JSON.stringify(object);
        console.log(jsonString);

        let response = await fetch('newBit', {
            method: 'POST',
            cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            headers: {
                'Content-Type': 'application/json'
            },
            async: true, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            processData: false,  //To avoid making query String instead of JSON
            body: jsonString
        });
        if (response.ok) {
            location.reload();
        } else {
            alert("Can't add bid.");
        }
    }
</script>


</html>

