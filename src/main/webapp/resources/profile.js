'use strict';

let personalInfo = {
  info:
    { userRole: 'seller', login: 'user1234',
      email: 'trololo@gmail.com', balance: 170, rate: 4.5},
  changes: false,

  showInfo: function () {
    let elem, p;

    elem = document.querySelectorAll('.personal-data__item');

    for (let i = 0; elem[i]; ++i) {
      p = document.createElement('p');
      p.setAttribute('class', 'personal-data__value');
      if (i === 0) {
        p.innerHTML = this.info.userRole;
        if (this.info.userRole === 'seller') {
          sellerFeatures.addSellersFeatures(p, this.info.rate);
        }
      } else if (i === 1) {
        p.innerHTML = this.info.login;
      } else if (i === 2) {
        p.innerHTML = this.info.email;
      } else {
        p.innerHTML = this.info.balance;
      }
      elem[i].appendChild(p);
    }
  },
  changeInfo: function () {
    let elem, p;

    if (this.changes) return; else this.changes = true;

    elem = document.querySelectorAll('.personal-data__item');
    for (let i = 1; elem[i]; ++i) {
      p = document.createElement('input');
      p.className = 'personal-data__value';
      p.placeholder = elem[i].lastChild.innerHTML;
      if (i === 1) {
        p.setAttribute('name', 'login');
        p.setAttribute('id', 'username');
        p.setAttribute('type', 'text');
      } else if (i === 2) {
        p.setAttribute('name', 'email');
        p.setAttribute('id', 'email');
        p.setAttribute('type', 'email');
      } else {
        p.setAttribute('name', 'balance');
        p.setAttribute('id', 'balance');
        p.setAttribute('type', 'number');
        p.setAttribute('size', '5');
        p.setAttribute('step', '10');
        p.setAttribute('min', '100');
        p.setAttribute('max', '50000');
      }
      elem[i].removeChild(elem[i].lastChild);
      elem[i].appendChild(p);
    }
    this.addSubmitCancel();
  },
  changePassword: function () {
    let elem, p;

    if (this.changes) return; else this.changes = true;

    document.querySelector('#username').innerHTML = 'Old password';
    document.querySelector('#email').innerHTML = 'New password';
    document.querySelector('#balance').innerHTML = 'Confirm new password';

    elem = document.querySelectorAll('.personal-data__item');
    for (let i = 1; elem[i]; ++i) {
      p = document.createElement('input');
      p.className = 'personal-data__value';
      if (i === 1) {
        p.setAttribute('id', 'oldPassword');
        p.setAttribute('type', 'text');
      } else if (i === 2) {
        p.setAttribute('id', 'newPassword');
        p.setAttribute('type', 'text');
      } else {
        p.setAttribute('id', 'confirmNewPassword');
        p.setAttribute('type', 'text');
      }
      elem[i].removeChild(elem[i].lastChild);
      elem[i].appendChild(p);
    }
    this.addSubmitCancel();
  },
  addSubmitCancel: function () {
    let elem = document.querySelector('.personal-section');
      let header = document.createElement('div');
      header.className = 'personal-section__header';
        let h3 = document.createElement('h3');
        h3.className = 'personal-section__heading';
        h3.innerHTML = '';
      header.appendChild(h3);
        let buttons = document.createElement('div');
        buttons.className = 'personal-section__buttons';
          let in1 = document.createElement('input');
          in1.className = 'button';
          in1.setAttribute('value', 'Submit');
          in1.setAttribute('type', 'button');
          in1.setAttribute('onclick', 'submitChanges()');
          let in2 = document.createElement('input');
          in2.className = 'button';
          in2.setAttribute('value', 'Cancel');
          in2.setAttribute('type', 'button');
          in2.setAttribute('onclick', 'cancelChanges()');
        buttons.appendChild(in1);
        buttons.appendChild(in2);
      header.appendChild(buttons);
    elem.appendChild(header);
  }
}

let sellerFeatures = {
  addSellersFeatures: function (item, rating) {
    this.addStar(item, rating);
    this.addButtonFeatures();
  },
  addStar: function(item, rating) {
    item.innerHTML += '&emsp;';

    let span = document.createElement('span');
    span.setAttribute('class', 'seller-rating fa fa-fw fa-star field-icon');
    item.appendChild(span)

    span = document.createElement('span');
    span.setAttribute('class', 'seller-rating');
    if (rating !== 0) {
      span.innerHTML = rating;
    } else {
      span.innerHTML = '-';
    }
    item.appendChild(span);
  },
  addButtonFeatures: function() {
    let item = document.querySelector('#profile-buttons');

    let a = document.createElement('a');
    a.setAttribute('class', 'button');
    a.setAttribute('href', '#');
    a.setAttribute('onclick', 'viewFeedbacks(this)');
    a.innerHTML = 'Bidders feedbacks';

    item.appendChild(a);
  }
}


//   success: function (response) {
//     console.log("SUCCESS : ", data);
//     var json = JSON.parse(response);
//     console.log(json);
//     var newImgPath =  path + "/uploadFiles/" + json.newFile;
//     $('#newImg').attr("src", newImgPath);
//     $('#download').attr("href", newImgPath);
//     $('#load').attr("style", "visibility: visible");
//     $('#newImgDiv').attr("style", "display: initial");

let lots = {
  active: [
    { id: 14,name: 'Jam',brand: 'J\'ELITE',taste: 'orange rum',sizeG: 220,
      price: 8.99,active: 1,description: 'Ukrainian candied.',
      image: 'resources/favicon.ico'
    },
    { id: 15,name: 'Jam',brand: 'J\'ELITE',taste: 'strawberry basil tomato',sizeG: 220,
      price: 8.99,active: 1,description: 'Ukrainian candied.',
      image: 'resources/favicon.ico'
    }
  ],
  closed: [
    { id: 1,name: 'Jam',brand: 'St.Dalfout',taste: 'blueberry',sizeG: 284,
      price: 9.99,active: 0,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 2,name: 'Jam',brand: 'St.Dalfout',taste: 'fig',sizeG: 284,
      price: 9.99,active: 0,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 3, name: 'Jam', brand: 'St.Dalfout', taste: 'raspberry', sizeG: 284,
      price: 9.99, active: 0, description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    }
  ],
  showActive: function() {
    let elem = document.querySelector('.active-lots');

    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.active)
      products.showItem(elem, this.active[i]);
  },
  showClosed: function() {
    let elem = document.querySelector('.closed-lots');

    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.closed)
      products.showItem(elem, this.closed[i]);
  },
}

function changeInfo() {
  // location.reload();
  personalInfo.changeInfo();
}
function changePassword() {
  // location.reload();
  personalInfo.changePassword();
}
function cancelChanges() {
  location.reload();
  this.changes = false;
}
/**
 *  ------------------------- ДОПИСАТЬ submitChanges / отправка на сервер
 * */
function submitChanges() {
  console.log('submit');
  location.reload();
  this.changes = false;
}
/**
 *  ------------------------- ДОПИСАТЬ signOut / отправка на сервер
 * */
function signOut() {
  console.log('Log out');
}

let init = () => {
  // personalInfo.showInfo();
  lots.showActive();
  lots.showClosed();
}

window.onload = init;

