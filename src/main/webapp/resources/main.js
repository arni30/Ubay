'use strict';

// let crumbs = {
//   addCrumb: function(str) {
//     let node = document.querySelector('.crumbs');
//     let li = document.createElement('li');
//     let a = document.createElement('a');
//     a.setAttribute('href', '#');
//     a.innerHTML = str;
//
//     li.appendChild(a);
//     node.appendChild(li);
//   },
//   removeCrumb: function() {
//     let node = document.querySelector('.crumbs');
//     node.removeChild(node.lastElementChild);
//   }
// }

// let actions = {
//   countTotal: function() {
//     let total = 0;
//     for (let i in basket.shopList) {
//       total += basket.shopList[i].qty;
//     }
//     return total;
//   },
//   recountBasket: function() {
//     let list;
//
//     basket.total = 0;
//     for (let i = 0; i < basket.shopList.length; ++i) {
//       list = basket.shopList[i];
//       list.subtotal = list.price * list.qty;
//       basket.total += list.subtotal;
//     }
//   }
// }


let view = {
  sort: function() {
    let select = event.target;

    if (select.selectedIndex === 1 && !products.sortCheap) {
      products.sortCheap = true;
      products.sortExpen = false;
      products.items.sort((a, b) => (a.price > b.price) ? 1 : -1);
    }
    else if (select.selectedIndex === 2 && !products.sortExpen) {
      products.sortCheap = false;
      products.sortExpen = true;
      products.items.sort((a, b) => (a.price < b.price) ? 1 : -1);
    }
    products.showItems();
    document.querySelectorAll('.item_add')
          .forEach(item => item.addEventListener('click', view.addToBasket));
  },
  filters: function() {
    let arr = [],
        boxes = document.querySelectorAll('.check-brand');
    for (let i = 0; i < boxes.length; ++i)
      if (boxes[i].checked) {
        arr.push(boxes[i].getAttribute('value'));
      }

    if (arr.length !== 0)
      products.showFilteredItems(arr);
    else
      products.showItems();

    document.querySelectorAll('.item_add')
            .forEach(item => item.addEventListener('click', view.addToBasket));
  }
}


let init = () => {
  products.showItems();
  document.querySelector('.sort-select').onchange = view.sort;
  document.querySelectorAll('.check-brand')
          .forEach(item => item.addEventListener('change', view.filters));
}

window.onload = init;
