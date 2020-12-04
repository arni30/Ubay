'use strict';

let auctions = {
  // lot: {active: false, id: 125, image: 'image', title: 'Book "Harry Hotter"', seller: 'trohalska', rate: 4.8,
  //   price: 17.5, priceStep:2.5, description: 'ololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololoololo trololo',
  //   startTime: '2020-11-18T10:18:00', endTime: '2020-11-20T10:18:00'},
  lot: {active: true, id: 125, image: 'image', title: '', seller: 'trohalska', rate: 4.8,
    price: 1, priceStep: 1, description: '',
    startTime: '2020-11-18T10:18:00', endTime: '2020-11-28T10:18:00'},
  userType: null,
  authorizedUser: null,
  winner: {bidder: null, feedback: null},

  hideElement: function (str) {
    let buttons = document.querySelector(str);
    buttons.setAttribute('style', 'display: none;');
  },
  activateElement: function (str) {
    let form = document.querySelector(str);
    form.setAttribute('style', 'display: block;');
  },
  flexElement: function (str) {
    let form = document.querySelector(str);
    form.setAttribute('style', 'display: flex;');
  },

  show: function () {
    document.querySelector('#lotId').innerHTML += this.lot.id;
    document.querySelector('#title').innerHTML = this.lot.title;
    document.querySelector('#price').innerHTML += '\$' + this.lot.price;
    document.querySelector('#aboutProfile').innerHTML = this.lot.seller;
    document.querySelector('#rate').innerHTML = (this.lot.rate === 0) ? ' - ' : this.lot.rate;
    document.querySelector('#startTime').innerHTML += this.lot.startTime.replace('T', '&emsp;');
    document.querySelector('#description').innerHTML = this.lot.description;

    if (this.lot.image) {
      document.querySelector('.lot-img img')
          .setAttribute('src', 'data:image/jpg;base64,' + this.lot.image);
    } else {
      document.querySelector('.lot-img img')
          .setAttribute('src', 'resources/favicon.ico');
    }

    if (this.lot.active) {
      if (this.userType && this.userType === 'bidder')
        this.activateElement('#addBit-buttons');
    } else {
      this.setClosedTimer();
      this.addClosedContent();
    }
  },
  addBit: function() {
    this.hideElement('#addBit-buttons');
    this.activateElement('#newBit');
    let label = document.querySelector('#newBit label');
    label.innerHTML += `(step \$${this.lot.priceStep})`;

    let newPrice = document.querySelector('#newPrice');
    let np = `${this.lot.price + this.lot.priceStep}`;
    newPrice.setAttribute('placeholder', np);
    newPrice.setAttribute('min', np);
    newPrice.setAttribute('step', this.lot.priceStep);
  },
  addClosedContent: function () {
    this.flexElement('#winner');
    let label = document.querySelector('#winner p');
    if (this.winner.bidder) {
      label.innerHTML = `Winner: <span>${this.winner.bidder}</span>`;
      if (this.winner.bidder === this.authorizedUser) {
        document.querySelector('#add-feedback-button')
            .setAttribute('style', 'display: block;');
      }
    }
    if (this.winner.feedback) {
      document.querySelector('#feedback').innerHTML += `Feedback: ${this.winner.feedback}`;
      this.hideElement('#winner a');
    } else {
      this.hideElement('#feedback');
    }
  },
  setClosedTimer: function () {
    let timer_field = document.getElementById('timer');
    timer_field.innerHTML = `CLOSED`;
    timer_field.setAttribute('style', 'color: red');
  },

  addFeedback: function (item) {
    if (this.winner.feedback === '') {
      item.setAttribute('href',
          '/ubay/addFeedback?lotId=' + this.lot.id);

    }
  }
}

let timer = setInterval(function () {
  let time_left = new Date(auctions.lot.endTime) - new Date();
  let timer_field = document.getElementById('timer');

  if (time_left <= 0) {
    clearInterval(timer);
    // auctions.addClosedContent();
  } else {
    let res = new Date(time_left);

    let days = `${res.getUTCDate() - 1} day`;
    if ((res.getUTCDate() - 1) !== 1) {
      days += 's';
    }
    let times = [ `${res.getUTCHours()}`,
                  `${res.getUTCMinutes()}`,
                  `${res.getUTCSeconds()}` ];
    for (let i in times) {
      if (times[i] < 10) {
        times[i] = '0' + times[i];
      }
    }
    timer_field.innerHTML = `${days} ${times[0]}:${times[1]}:${times[2]}`;
  }
}, 1000)

let init = () => {
  auctions.show();
  // timer();
}

window.onload = init;

