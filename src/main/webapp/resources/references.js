// function goto(str) {
//     // let url = location.href;
//     // let i_end = url.lastIndexOf('/') + 1;
//     // url = url.substring(0, i_end) + str;
//     // console.log(url);
//     // location.replace(url);
//     location.replace('http://localhost:8080/ubay/'+str);
// }

// /**
//  * delay for registration & authorization pages
//  */
//
// function goto(str) {
//     location.replace(str);
// }
// function gotoSign(str) {
//     delay(goto, 1500, str);
// }


/**
 * add login to url for view profile & feedbacks pages
 */
function viewProfile(item) {
    item.setAttribute('href',
        `/ubay/viewProfile?login=${document.querySelector('#aboutProfile').innerHTML}`);
}
function viewBidderProfile(item) {
    item.setAttribute('href',
        `/ubay/viewProfile?login=${item.innerHTML}`);
}
function viewFeedbacks(login) {
    document.querySelector('#biddersFeedbacks')
        .setAttribute('href',`/ubay/feedbacks?login=${login}`);
}

/**
 * add lot id to url for auction pages
 */
function gotoAuction(item) {
    location.replace(`/ubay/auction?lotId=${item.id}`);
}

/**
 * util for parse url
 */
function receiveGetParameters() {
    let param = [];
    let i = 0;
    location.search.substr(1).split("&")
        .forEach(function(item) {
            param[i++] = item.split("=");
        })
    return param;
}

setAuthorizedUser = (user) => {
    document.querySelector('#header-login-first').setAttribute('style', 'display: none;');
    document.querySelector('#header-login-second').setAttribute('style', 'display: block;');
    document.querySelector('#authorizedLogin').innerHTML = user;
}
