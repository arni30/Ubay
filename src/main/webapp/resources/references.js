function goto(str) {
    let url = location.href;
    let i_end = url.lastIndexOf('/') + 1;
    url = url.substring(0, i_end) + str;
    location.assign(url);
}