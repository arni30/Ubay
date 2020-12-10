'use strict';

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
  submitSearch: async function () {
    let title = document.querySelector('#searchByTitle');
    let price = document.querySelector('#searchByOpenPrice');
    let rate = document.querySelector('#searchBySellersRate');
    let duration = document.querySelector('#searchByDuration');
    let startTime = document.querySelector('#searchByStartTime');
    let description = document.querySelector('#searchByDescription');

    let formData = new FormData();
    formData.append('title', title.value);
    formData.append('price', price.value);
    formData.append('rate', rate.value);
    formData.append('duration', duration.value);
    formData.append('startTime', startTime.value);
    formData.append('description', description.value);
    let object = {};
    formData.forEach(function(value, key){
      object[key] = value;
    });
    let jsonString = JSON.stringify(object);
    console.log(jsonString);

    let response = await fetch('search', {
      method: 'POST',
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      headers: {
        'Content-Type' : 'application/json'
      },
      async: true, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
      processData: false,  //To avoid making query String instead of JSON
      body: jsonString
    }).then((response) => {
      return response.json();
    }).then((data) => {
      // console.log(data);
      products.items = data;
      products.items.reverse();
      products.showItems();
    }).catch((e) => {
      console.log(e);
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
