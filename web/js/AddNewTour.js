/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 29-05-2023      1.0                 DucTM           First Implement
 */
let dateNow = new Date();
document.getElementById("available_from").valueAsDate = dateNow;
document.getElementById("available_to").valueAsDate = dateNow;

document.getElementById("available_from").min=dateNow.toLocaleDateString();
document.getElementById("available_from").max=addDays(dateNow, 365);


image.onchange = () => {
    const file = image.files[0];
    if (file) {
        imageDisplay.src = URL.createObjectURL(file)
    }
}
function addDays(date, days) {
  date.setDate(date.getDate() + days);
  return date;
}
