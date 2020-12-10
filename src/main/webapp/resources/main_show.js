'use strict';

let products = {
  items: [
    { id: 0, title: '', category: 'other',
      startPrice: 0,  lastBidPrice: 0, lastBidder: '', active: true, description: 'NONE',
      image: 'resources/favicon.ico', bidderPrice: 0
    }
  ],
  sortCheap: false,
  sortExpen: false,
  showFilteredItems: function(arr) {
    let elem = document.querySelector('.container');

    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.items) {
      for (let j = 0; j < arr.length; ++j) {
        if (this.items[i].category === arr[j]) {
          this.showItem(elem, this.items[i]);
          break;
        }
      }
    }
  },
  showItems: function() {
    let elem = document.querySelector('.container');

    if (elem.firstChild)
      while (elem.firstChild)
        elem.removeChild(elem.lastChild);

    for (let i in this.items)
      this.showItem(elem, this.items[i], false);
  },
  showItem: function(elem, item, forProfile) {
    let node, shelf, p, p1;

    if (item.lastBidPrice) {
      item.startPrice = item.lastBidPrice;
    } else {
      item.lastBidPrice = item.startPrice;
    }
    node = document.createElement('div');
    node.className = 'one-third column';
    shelf = document.createElement('div');
    shelf.className = 'shelfItem';

    p1 = document.createElement('div');
    p1.className = 'images';
      p = document.createElement('img');
      p.className = 'item_thumb';
      if (item.image) {
        p.setAttribute('src', 'data:image/jpg;base64,' + item.image);
      } else {
        p.setAttribute('src', 'resources/favicon.ico');
      }
      p1.appendChild(p);

      if (!item.active) {
        p = document.createElement('img');
        p.className = 'item_thumb closed-item';
        p.setAttribute('src', 'resources/closed-stamp.png');
        p1.appendChild(p);
      }
    shelf.appendChild(p1);

    p = document.createElement('h5');
    p.className = 'item_name';
    p.innerHTML = item.title;
    shelf.appendChild(p);

    p = document.createElement('p');
    p.className = 'item_description';
    if (forProfile === true) {
      p.innerHTML = `Your last bid = \$${item.bidderPrice}`;
      if (item.bidderPrice < item.lastBidPrice) {
        p.innerHTML += '. It\'s not final!';
        p.setAttribute('style', 'color: red;');
      }
    } else {
      if (item.description.length > 45) {
        p.innerHTML = item.description.substring(0, 40) + "...";
      } else {
        p.innerHTML = item.description;
      }
    }

    shelf.appendChild(p);

    p = document.createElement('div');
    p.className = 'shelfDescribe';
    p1 = document.createElement('span');
    p1.className = 'item_price';
    p1.innerHTML = '\$' + item.startPrice;
    p.appendChild(p1);

    p1 = document.createElement('input');
    p1.setAttribute('id', item.id);
    p1.setAttribute('value', 'show details');
    if (item.startPrice !== 0) {
      p1.setAttribute('type', 'button');
      p1.setAttribute('onclick', 'gotoAuction(this)');
      p1.className = 'item_add button';
    }
    else
      p1.className = 'button-empty';
    p.appendChild(p1);

    shelf.appendChild(p);

    node.appendChild(shelf);
    elem.appendChild(node);
  }
}
