'use strict';

let feedbacks = {
  sellerInfo: {username: 'trohalska', rate: 4.8},

  feedbackList:[
    {title: 'book "Harry Potter"', bidder: 'Vasya Petechkin', rate: '5.0', feedback: 'All good'}
  ],

  showInfo: function () {
    let elem = document.querySelector('.feedbacks-header a');
    elem.firstChild.innerHTML = this.sellerInfo.username;

    if (this.sellerInfo.rate !== 0) {
      document.querySelector('#rate').innerHTML = this.sellerInfo.rate.toFixed(1);
    }

  },
  showItems: function () {
    let elem = document.querySelector('.feedbacks-container');
    for (let i in this.feedbackList)
      this.showItem(elem, this.feedbackList[i]);
  },

  showItem: function (elem, item) {
    let node, section, header, h3, div, a, label, p;

    node = document.createElement('div');
    node.className = 'contact-info';
      section = document.createElement('section');
      section.className = 'personal-section';
        header =  document.createElement('div');
        header.className = 'personal-section__header';
          h3 = document.createElement('h4');
          h3.className = 'personal-section__heading';
          h3.innerHTML = item.title;
          div = document.createElement('div');
            a = document.createElement('a');
            a.setAttribute('id', 'bidder');
            a.setAttribute('onclick', 'viewBidderProfile(this)');
            a.setAttribute('href', '#');
            a.innerHTML = item.bidder;
            label = document.createElement('label');
            label.className = 'personal-data__label';
            label.innerHTML = '&emsp; rated at ' + item.rate;
          div.appendChild(a);
          div.appendChild(label);
        header.appendChild(h3);
        header.appendChild(div);
      section.appendChild(header);
      p = document.createElement('p');
      p.innerHTML = item.feedback;
      section.appendChild(p);
    node.appendChild(section);

    elem.appendChild(node);
  }
}

let init = () => {
  feedbacks.showInfo();
  feedbacks.showItems();
}

window.onload = init;

