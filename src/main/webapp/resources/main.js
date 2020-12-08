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
      products.items.sort((a, b) => (a.startPrice > b.startPrice) ? 1 : -1);
    }
    else if (select.selectedIndex === 2 && !products.sortExpen) {
      products.sortCheap = false;
      products.sortExpen = true;
      products.items.sort((a, b) => (a.startPrice < b.startPrice) ? 1 : -1);
    }
    products.showItems();
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
  },
  submitSearch: function () {
    let title = document.querySelector('#searchByTitle');
    let price = document.querySelector('#searchByOpenPrice');
    let duration = document.querySelector('#searchByDuration');
    let startTime = document.querySelector('#searchByStartTime');
    let description = document.querySelector('#searchByDescription');

    let formData = new FormData();
    formData.append('title', title.value);
    formData.append('price', price.value);
    formData.append('duration', duration.value);
    formData.append('startTime', startTime.value);
    formData.append('description', description.value);
    let object = {};
    formData.forEach(function(value, key){
      object[key] = value;
    });
    let jsonString = JSON.stringify(object);
    console.log(jsonString);

    $.ajax({
      url : 'search',
      type : 'POST',
      contentType : "application/json; charset=utf-8",
      data : jsonString,
      async: true, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
      processData : false,  //To avoid making query String instead of JSON
      cache: false, //This will force requested pages not to be cached by the browser
      success : function(resposeJsonObject) {
        alert("Yes");
        console.log(resposeJsonObject);
      },
      error : function(err) {
        alert("nope!");
        // alert(err);
      }
    });
  }
}


let init = () => {
  products.items.reverse();
  products.showItems();
  document.querySelector('.sort-select').onchange = view.sort;
  document.querySelectorAll('.check-brand')
          .forEach(item => item.addEventListener('change', view.filters));
}

window.onload = init;
