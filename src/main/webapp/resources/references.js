// function goto(str) {
//     // let url = location.href;
//     // let i_end = url.lastIndexOf('/') + 1;
//     // url = url.substring(0, i_end) + str;
//     // console.log(url);
//     // location.replace(url);
//     location.replace('http://localhost:8080/ubay/'+str);
// }

function viewProfile(item) {
    item.setAttribute('href',
        `/ubay/viewProfile?login=${document.querySelector('#aboutProfile').innerHTML}`);
}
function viewBidderProfile(item) {
    item.setAttribute('href',
        `/ubay/viewProfile?login=${item.innerHTML}`);
}
function viewFeedbacks(item) {
    item.setAttribute('href',
        `/ubay/feedbacks?login=${document.querySelector('#aboutProfile').innerHTML}`);
}
function gotoAddLot(login, userId) {
    document.querySelector('#addLotId')
        .setAttribute('href', `/ubay/addLot?login=${login}&userId=${userId}`);
}
function gotoAuction(item) {
    location.replace(`/ubay/auction?lotId=${item.id}`);
}

function receiveGetParameters() {
    let param = [];
    let i = 0;
    location.search.substr(1).split("&")
        .forEach(function(item) {
            param[i++] = item.split("=");
        })
    return param;
}