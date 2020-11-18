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

$(".toggle-password").click(function() {
    $(this).toggleClass("fa-eye fa-eye-slash");
    let input = $($(this).attr("toggle"));
    if (input.attr("type") === "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});

// $(function () {
// 	$(".btn-signin").click(function() {
//         $(".btn-animate").toggleClass("btn-animate-grow");
//         $(".welcome").toggleClass("welcome-left");
//         $(".cover-photo").toggleClass("cover-photo-down");
//         $(".frame").toggleClass("frame-short");
//         $(".profile-photo").toggleClass("profile-photo-down");
//         $(".btn-goback").toggleClass("btn-goback-up");
//         $(".forgot").toggleClass("forgot-fade");
//         $(".welcome-block").toggleClass("welcome-block-up");
// 	});
// });

function signin() {
    let loginUsername = document.querySelector('#loginLogin').value;
    let loginPassword = document.querySelector('#passwordLogin').value;

    if (!loginUsername || !loginPassword) {
        alert('All fields have to be filled!');
    } else {
        // let formData = new FormData();
        // formData.set('type', 'signin');
        // formData.set('username', loginUsername);
        // formData.set('password', loginPassword);
        // send(formData);
    }
}

function checkEmail() {
    document.querySelector('.nav').setAttribute('class', 'nav nav-up');
    document.querySelector('.form-signup-left')
        .setAttribute('class', 'form-signup-left form-signup-down');
    document.querySelector('.success').setAttribute('class', 'success success-left');
    document.querySelector('.frame').setAttribute('class', 'frame frame-short');
}

// $(function() {
// 	$(".btn-signup").click(function() {
//         $(".nav").toggleClass("nav-up");
//         $(".form-signup-left").toggleClass("form-signup-down");
//         $(".success").toggleClass("success-left");
//         $(".frame").toggleClass("frame-short");
// 	});
// });

function signup() {
    let role = document.querySelector('#userRole').value;
    let balance = document.querySelector('#balance').value;
    let username = document.querySelector('#loginSignUp').value;
    let password = document.querySelector('#passwordSignUp').value;
    let cpassword = document.querySelector('#confirmpassword').value;
    let email = document.querySelector('#email').value;
    if (!username || !password || !cpassword || !email) {
        alert('All fields have to be filled!');
    } else if (balance < 1 || balance > 50000) {
        alert('Balance should be in range 1-50000!');
    } else if (password !== cpassword) {
        alert('Passwords are different!');
    } else {
        // checkEmail();
        // let formData = new FormData();
        // formData.set('type', 'signup');
        // formData.set('role', role);
        // formData.set('balance', balance);
        // formData.set('username', username);
        // formData.set('password', password);
        // formData.set('confirmpassword', cpassword);
        // formData.set('email', email);

        // formData.append('form', new Blob([JSON.stringify({
        //     "type": "signup",
        //     "role": role,
        //     "balance": balance,
        //     "username": username,
        //     "password": password,
        //     "confirmpassword": cpassword,
        //     "email": email
        // })]
        //     , {
        //     type: "application/json"
        // }
        // ));
        //
        // send(formData);
    }
}


async function send(formData) {

    // let request = new XMLHttpRequest();
    // request.open("POST", 'http://localhost:8080/ubay/authorization');
    // request.send(formData);

    fetch('http://localhost:8080/ubay/authorization', {
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
        //   'Content-type': 'application/json'
        // },
        body: formData
    }).then(function (response) {
        if (response.status !== 200) {
            alert("There was an error! Response != 200");
        } else {
            // alert("Request successful");
        }
    }).catch(function (err) {
        alert("There was an error!");
    });
    // if (response.ok) {
    //     // let blob = await response.blob();
    //     // let reader = new FileReader();
    //     // reader.onloadend = function(event){
    //     //     resultImg.src = event.target.result;
    //     //     resultImg.style.display = 'block';
    //     // };
    //     // reader.readAsDataURL(blob);
    // } else {
    //     alert('Error in server post method');
    // }
}



// function onSubmit() {
//
//     let formData = new FormData();
//
//     formData.append("file", document.forms["userForm"].file.files[0]);
//     formData.append('user', new Blob([JSON.stringify({
//         "firstName": document.getElementById("firstName").value,
//         "lastName": document.getElementById("lastName").value
//     })], {
//         type: "application/json"
//     }));
//
//     fetch('/upload', {
//         method: 'post',
//         body: formData
//     }).then(function (response) {
//         if (response.status !== 200) {
//             alert("There was an error!");
//         } else {
//             alert("Request successful");
//         }
//     }).catch(function (err) {
//         alert("There was an error!");
//     });;
// }