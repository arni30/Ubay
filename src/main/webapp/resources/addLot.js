'use strict';

function durationNumber() {
  document.getElementById('duration').addEventListener('click', function () {
    let number = document.getElementById('duration').value;
    document.getElementById('durationNumber').textContent = number + ' day';
    if (number > 1) {
      document.getElementById('durationNumber').textContent += 's';
    }
  });
}

let init = () => {
  durationNumber();
}

window.onload = init;

