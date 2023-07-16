Date.prototype.addYears = function (years) {
    let date = new Date(this);
    date.setYear(date.getFullYear() + years);
    return date;
};
let dob = document.getElementById("dob");
dob.max = new Date().addYears(-18).toISOString().split("T")[0];
$('#paymentForm').hide();
$('#addPayment').click(() => {
    $('#paymentForm').show();
});
$('#cancel').click(() => {
    $('#paymentForm').hide();
});
$('#qr').on("change", (e)=>{
    const file = e.target.files[0];
    if (file) {
        $('#qrDisplay').attr("src", URL.createObjectURL(file));
    }
});
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


