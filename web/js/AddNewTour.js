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

from.onchange = () => {
    to.min = from.valueAsDate.addDays(1).toISOString().split("T")[0];
    to.valueAsDate = from.valueAsDate.addDays(1);
};

document.getElementById("available_from").min = dateNow.toISOString().split("T")[0];
from.valueAsDate = dateNow;
to.valueAsDate = dateNow.addDays(1);

let image=document.getElementById("image");
let imageDisplay=document.getElementById("imageDisplay");
image.onchange = () => {
    const file = image.files[0];
    if (file) {
        imageDisplay.src = URL.createObjectURL(file);
    }
};
