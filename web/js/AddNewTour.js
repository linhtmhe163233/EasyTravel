/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 29-05-2023      1.0                 DucTM           First Implement
 * 31-05-2023      1.0                 DucTM           Add date constraints
 */
let dateNow = new Date();
let from = document.getElementById("available_from");
let to = document.getElementById("available_to");

Date.prototype.addDays = function (days) {
    let date = new Date(this);
    date.setDate(date.getDate() + days);
    return date;
};

Date.prototype.addYears = function (years) {
    let date = new Date(this);
    date.setYear(date.getFullYear() + years);
    return date;
};

from.onchange = () => {
    to.min = from.valueAsDate.addDays(1).toISOString().split("T")[0];
    to.valueAsDate = from.valueAsDate.addDays(1);
};

from.max = dateNow.addYears(10).toISOString().split("T")[0];
to.max = dateNow.addYears(10).addDays(1).toISOString().split("T")[0];

let id = document.getElementById("id");
from.valueAsDate = dateNow;
if (id.value.length === 0) {
    to.valueAsDate = dateNow.addDays(1);
}

from.min = dateNow.toISOString().split("T")[0];
to.min = dateNow.addDays(1).toISOString().split("T")[0];

let image = document.getElementById("image");
let imageDisplay = document.getElementById("imageDisplay");

image.onchange = () => {
    const file = image.files[0];
    if (file) {
        imageDisplay.src = URL.createObjectURL(file);
    }
};

// xmlHTTP return blob respond
function getImgURL(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        callback(xhr.response);
    };
    xhr.open('GET', url);
    xhr.responseType = 'blob';
    xhr.send();
}
;

function loadURLToInputFiled(url) {
    getImgURL(url, (imgBlob) => {
        // Load img blob to input
        // WIP: UTF8 character error
        let fileName = imageDisplay.src.substring(imageDisplay.src.lastIndexOf('/')+1);
        console.log(fileName);
        let file = new File([imgBlob], fileName, {type: "image/*", lastModified: new Date().getTime()}, 'utf-8');
        let container = new DataTransfer();
        container.items.add(file);
        image.files = container.files;

    });
}
;
if (id.value.length !== 0) {
    loadURLToInputFiled(imageDisplay.src);
}
console.log();
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        let validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();
