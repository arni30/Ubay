$(function() {
	$(".btn").click(function() {
	    $(".form-signin").toggleClass("form-signin-left");
    $(".form-signup").toggleClass("form-signup-left");
    $(".frame").toggleClass("frame-long");
    $(".signup-inactive").toggleClass("signup-active");
    $(".signin-active").toggleClass("signin-inactive");
    $(".forgot").toggleClass("forgot-left");   
    $(this).removeClass("idle").addClass("active");
	});
});

$(function() {
	$(".btn-signup").click(function() {
  $(".nav").toggleClass("nav-up");
  $(".form-signup-left").toggleClass("form-signup-down");
  $(".success").toggleClass("success-left");
  $(".frame").toggleClass("frame-short");
	});
});

$(function() {
	$(".btn-signin").click(function() {
  $(".btn-animate").toggleClass("btn-animate-grow");
  $(".welcome").toggleClass("welcome-left");
  $(".cover-photo").toggleClass("cover-photo-down");
  $(".frame").toggleClass("frame-short");
  $(".profile-photo").toggleClass("profile-photo-down");
  $(".btn-goback").toggleClass("btn-goback-up");
  $(".forgot").toggleClass("forgot-fade");
	});
});

function signin() {
    send('.form-signin');
}

function signup() {
    send('.form-signup');
}

async function send(address) {

    // let img = inputFile.files[0];
    // let pixRange = document.getElementById('pixRange').value;

    let formData = new FormData();
    let reqForm = 'form' + address + ' input[name=';
    // formData.set('type', address.substring(6));
    // formData.set('username', document.querySelector(reqForm+'username]').value);
    // formData.set('password', document.querySelector(reqForm+'password]').value);
    // formData.set('confirmpassword', document.querySelector(reqForm+'confirmpassword]').value);
    // formData.set('email', document.querySelector(reqForm+'email]').value);

    // let request = new XMLHttpRequest();
    // request.open("POST", 'http://localhost:8080/ubay/servlet');
    // request.send(formData);

    let response = await fetch('http://localhost:8080/ubay/signin',{
        method: 'POST',
        enctype: 'multipart/form-data',
        body: JSON.stringify({
            'foo': 'bar'
        }),
        headers: {
            'Content-Type': 'application/json',
        },
        // body: JSON.stringify(formData),
        // headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        // },
    });
    if (response.ok) {
        // let blob = await response.blob();
        // let reader = new FileReader();
        // reader.onloadend = function(event){
        //     resultImg.src = event.target.result;
        //     resultImg.style.display = 'block';
        // };
        // reader.readAsDataURL(blob);
    } else {
        alert('Error in server post method');
    }
}