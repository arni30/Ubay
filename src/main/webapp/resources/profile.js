'use strict';

let personalInfo = {
  info: { userRole: '', login: '', email: '', balance: 0, avarageRate: 0 },

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
          sellerFeatures.addSellersFeatures(p, this.info.avarageRate.toFixed(1));
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
    viewFeedbacks(this.info.login);
  },
  changeInfo: function () {
    let elem, p;

    if (this.changes) return; else this.changes = true;

    elem = document.querySelectorAll('.personal-data__item');
    for (let i = 2; elem[i]; ++i) {
      p = document.createElement('input');
      p.className = 'personal-data__value';
      p.placeholder = elem[i].lastChild.innerHTML;
      // if (i === 1) {
      //   p.setAttribute('id', 'aboutProfile');
      //   p.setAttribute('type', 'text');
      // } else
      if (i === 2) {
        p.setAttribute('id', 'email');
        p.setAttribute('type', 'email');
      } else if (i === 3) {
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
    this.addSubmitCancel('personalInfo.submitChangeInfo()');
  },
  submitChangeInfo: async function () {
    let email = document.querySelector('#email');
    let balance = document.querySelector('#balance');

    if (!email.value && !balance.value) {
      return;
    } else if (!email.value) {
      email.value = this.info.email;
    } else if (!balance.value) {
      balance.value = this.info.balance;
    }

    let formData = new FormData();
    formData.append('newEmail', email.value);
    formData.append('newBalance', balance.value);
    let object = {};
    formData.forEach(function (value, key) {
      object[key] = value;
    });
    let jsonString = JSON.stringify(object);
    console.log(jsonString);

    let response = await fetch('changePersonalInfo', {
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
      console.log(response);
      alert("Can't change personal info");
      location.reload();
    }
  },
  changePassword: function () {
    let elem, p;

    if (this.changes) return; else this.changes = true;

    elem = document.querySelectorAll('.personal-data__label');
    for (let i = 1; elem[i]; ++i) {
      if (i === 1) {
        p = 'Old password';
      } else if (i === 2) {
        p = 'New password';
      } else if (i === 3) {
        p = 'Confirm new password';
      }
      elem[i].innerHTML = p;
    }

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
    this.addSubmitCancel('personalInfo.submitChangePassword()');
  },
  submitChangePassword: async function () {
    let oldPas = document.querySelector('#oldPassword');
    let newPas = document.querySelector('#newPassword');
    let confirm = document.querySelector('#confirmNewPassword');

    if (newPas.value && newPas.value !== confirm.value) {
      confirm.value = '';
      alert('Passwords do not match!');
      return;
    } else if (!oldPas.value) {
      return;
    }
    let formData = new FormData();
    formData.append('oldPassword', oldPas.value);
    formData.append('newPassword', newPas.value);
    let object = {};
    formData.forEach(function (value, key) {
      object[key] = value;
    });
    let jsonString = JSON.stringify(object);
    console.log(jsonString);

    let response = await fetch('changePassword', {
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
      console.log(response);
      alert("Can't change password. Old password is wrong!");
    }
  },
  addSubmitCancel: function (submitButton) {
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
          in1.setAttribute('onclick', submitButton);
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
    this.addButtonsFeatures();
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
      span.innerHTML = ' -';
    }
    item.appendChild(span);
  },
  addButtonsFeatures: function() {
    let item = document.querySelector('#profile-buttons');
    item.setAttribute('style', 'display = block;');

    if (personalInfo.info.login === document.querySelector('#authorizedLogin').innerHTML) {
      item = document.querySelector('#addLotId');
      item.setAttribute('style', 'display = block;');

    }
  }
}

let lots = {
  items: [
    { id: 1, title: 'NONE', category: '',
      price: 0, active: true, description: '',
      image: 'resources/favicon.ico'
    },
    { id: 2, title: 'NONE', category: '',
      price: 0, active: false, description: '',
      image: 'resources/favicon.ico'
    }
  ],
  showItems: function() {
    let elemActive = document.querySelector('.active-lots');
    if (elemActive.firstChild)
      while (elemActive.firstChild)
        elemActive.removeChild(elemActive.lastChild);

    let elem = document.querySelector('.closed-lots');
    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.items) {
      if (this.items[i].active) {
        document.querySelector('#activeBox')
            .setAttribute('style', 'display:flex');
        break;
      }
    }
    for (let i in this.items) {
      if (!this.items[i].active) {
        document.querySelector('#closedBox')
            .setAttribute('style', 'display:flex');
        break;
      }
    }

    for (let i in this.items) {
      if (this.items[i].active) {
        products.showItem(elemActive, this.items[i], this.isAuthorizedBidderBids());
      } else {
        products.showItem(elem, this.items[i], false);
      }
    }
  },
  isAuthorizedBidderBids: function() {
    if (personalInfo.info.userRole === 'bidder' &&
        personalInfo.info.login === document.querySelector('#authorizedLogin').innerHTML) {
      return true;
    }
    return false;
  }
}

function cancelChanges() {
  location.reload();
  this.changes = false;
}

let init = () => {
  personalInfo.showInfo();
  lots.showItems();
}

window.onload = init;

