'use strict';

let products = {
  items: [
    { id: 14, title: 'Jam', category: 'J\'ELITE',
      price: 8.99,active: 1,description: 'Ukrainian candied.',
      image: 'resources/favicon.ico'
    },
    { id: 15,title: 'Jam',category: 'J\'ELITE',
      price: 8.99,active: 2,description: 'Ukrainian candied.',
      image: 'resources/favicon.ico'
    },
    { id: 1,title: 'Jam',category: 'St.Dalfout',
      price: 9.99,active: 0,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 2,title: 'Jam',category: 'St.Dalfout',
      price: 9.99,active: 2,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 3,title: 'Jam',category: 'St.Dalfout',
      price: 9.99,active: 0,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 4,title: 'Jam',category: 'St.Dalfout',
      price: 6.99,active: 10,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 5,title: 'Jam',category: 'St.Dalfout',
      price: 9.99,active: 10,description: 'Very tasty jam.',
      image: 'resources/favicon.ico'
    },
    { id: 6,title: 'Jam',category: 'Chantaine',
      price: 11.99,active: 10,description: 'Natural jam, made in France.',
      image: 'resources/favicon.ico'
    },
    { id: 7,title: 'Jam',category: 'Chantaine',
      price: 11.99,active: 10,description: 'Natural jam, made in France.',
      image: 'resources/favicon.ico'
    },
    { id: 8,title: 'Jam',category: 'Chantaine',
      price: 11.99,active: 10,description: 'Natural jam, made in France.',
      image: 'resources/favicon.ico'
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
      this.showItem(elem, this.items[i]);
  },
  showItem: function(elem, item) {
    let node, shelf, p, p1;

    node = document.createElement('div');
    node.className = 'one-third column';
    shelf = document.createElement('div');
    shelf.className = 'shelfItem';

    p1 = document.createElement('div');
    p1.className = 'images';
      p = document.createElement('img');
      p.className = 'item_thumb';
      p.setAttribute('src', item.image);
      p1.appendChild(p);

      if (!item.active) {
        p = document.createElement('img');
        p.className = 'item_thumb closed-item';
        // p.setAttribute('style', '')
        p.setAttribute('src', 'resources/closed-stamp.png');
        p1.appendChild(p);
      }
    shelf.appendChild(p1);

    p = document.createElement('h5');
    p.className = 'item_name';
    p.innerHTML = item.title + ' ' + item.category;
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
    p1.setAttribute('id', item.id);
    p1.setAttribute('value', 'show details');
    // if (item.active !== 0) {
      p1.setAttribute('type', 'button');
      p1.setAttribute('onclick', 'gotoAuction(this)');
      p1.className = 'item_add button';

    // }
    // else
    //   p1.className = 'button-empty';
    p.appendChild(p1);

    shelf.appendChild(p);

    node.appendChild(shelf);
    elem.appendChild(node);
  }
}
