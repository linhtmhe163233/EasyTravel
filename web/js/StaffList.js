/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 03-06-2023      1.0                 DucTM           First Implement
 */



let dob = document.getElementById("dob");

Date.prototype.addYears = function (years) {
    let date = new Date(this);
    date.setYear(date.getFullYear() + years);
    return date;
};
dob.max = new Date().addYears(-18).toISOString().split("T")[0];


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

