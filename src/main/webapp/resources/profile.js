'use strict';

let personalInfo = {
  info: [
    {role: 'seller', username: 'user1234', email: 'trololo@gmail.com', balance: 170}
  ],
  changes: false,

  showInfo: function () {
    let elem, p;
    elem = document.querySelectorAll('.personal-data__item');

    for (let i = 0; elem[i]; ++i) {
      p = document.createElement('p');
      p.className = 'personal-data__value';
      if (i === 0) {
        p.innerHTML = this.info[0].role;
      } else if (i === 1) {
        p.innerHTML = this.info[0].username;
      } else if (i === 2) {
        p.innerHTML = this.info[0].email;
      } else {
        p.innerHTML = this.info[0].balance;
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
        p.setAttribute('id', 'username');
        p.setAttribute('type', 'text');
      } else if (i === 2) {
        p.setAttribute('id', 'email');
        p.setAttribute('type', 'email');
      } else {
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
        h3.className = 'personal-s    ection__heading';
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




//   success: function (response) {
//     console.log("SUCCESS : ", data);
//     var json = JSON.parse(response);
//     console.log(json);
//     var newImgPath =  path + "/uploadFiles/" + json.newFile;
//     $('#newImg').attr("src", newImgPath);
//     $('#download').attr("href", newImgPath);
//     $('#load').attr("style", "visibility: visible");
//     $('#newImgDiv').attr("style", "display: initial");

let products = {
  items: [
    { code: 14,name: 'Jam',brand: 'J\'ELITE',taste: 'orange rum',sizeG: 220,
      price: 8.99,amountAvailable: 1,description: 'Ukrainian candied.',
      image: 'https://i.postimg.cc/dtpZxL0L/14.png'
    },
    { code: 15,name: 'Jam',brand: 'J\'ELITE',taste: 'strawberry basil tomato',sizeG: 220,
      price: 8.99,amountAvailable: 2,description: 'Ukrainian candied.',
      image: 'https://i.postimg.cc/wjNR8gtF/15.png'
    },
    { code: 1,name: 'Jam',brand: 'St.Dalfout',taste: 'blueberry',sizeG: 284,
      price: 9.99,amountAvailable: 0,description: 'Very tasty jam.',
      image: 'https://i.postimg.cc/13SBjCXg/1.jpg'
    },
    { code: 2,name: 'Jam',brand: 'St.Dalfout',taste: 'fig',sizeG: 284,
      price: 9.99,amountAvailable: 2,description: 'Very tasty jam.',
      image: 'https://i.postimg.cc/4Ndp0DbF/2.jpg'
    },
    { code: 3, name: 'Jam', brand: 'St.Dalfout', taste: 'raspberry', sizeG: 284,
      price: 9.99, amountAvailable: 10, description: 'Very tasty jam.',
      image: 'https://i.postimg.cc/J42jCpp7/3.jpg'
    }
  ],
  showItems: function() {
    let elem = document.querySelector('.container');

    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.items)
      this.showItem(elem, this.items[i]);
  },


  showItem: function(elem, item) {
    let node, shelf, p, p1;

    node = document.createElement('div');
    node.className = 'one-third column';
    shelf = document.createElement('div');
    shelf.className = 'shelfItem';

    p = document.createElement('img');
    p.className = 'item_thumb';
    p.setAttribute('src', item.image);
    shelf.appendChild(p);

    p = document.createElement('h5');
    p.className = 'item_name';
    p.innerHTML = item.name + ' ' + item.brand + ' ' + item.taste + ', ' + item.sizeG + 'g';
    shelf.appendChild(p);

    p = document.createElement('p');
    p.className = 'item_description';
    p.innerHTML = item.description;
    shelf.appendChild(p);

    p = document.createElement('div');
    p.className = 'shelfDescribe';
      p1 = document.createElement('span');
      p1.className = 'item_price';
      p1.innerHTML = '\$' + item.price;
      p.appendChild(p1);

      p1 = document.createElement('input');
      p1.setAttribute('code', item.code);
      p1.setAttribute('value', 'Make offer');
      if (item.amountAvailable !== 0) {
        p1.setAttribute('type', 'button');
        p1.className = 'item_add button';
      }
      else
        p1.className = 'button-empty';
      p.appendChild(p1);

    shelf.appendChild(p);

    p = document.createElement('span');
    if (item.amountAvailable === 0) {
      p.className = 'notavlb';
      p.innerHTML = 'not available';
    } else {
      p.className = 'avlb';
      p.innerHTML = 'available';
      if (item.amountAvailable <= 3)
        p.innerHTML = 'last ' + item.amountAvailable + ' available';
    }
    shelf.appendChild(p);

    node.appendChild(shelf);
    elem.appendChild(node);
  }
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
  location.replace("http://localhost:8080/ubay/main");
}

let init = () => {
  personalInfo.showInfo();
  products.showItems();
}

window.onload = init;

