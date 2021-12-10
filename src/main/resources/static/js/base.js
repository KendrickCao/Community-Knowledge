/*
javaScript: Menu bar activate and deactivate
 */

const btnHam = document.querySelector('.ham-btn');
const btnTimes = document.querySelector('.times-btn');
const navBar = document.getElementById('nav-bar');

// Creating the EventListener for the btnHam
btnHam.addEventListener('click', function (){
    if (btnHam.className !== "") {
        btnHam.style.display = "none";
        btnTimes.style.display = "block";
        navBar.classList.add("show-nav");
    }
})
// Creating the EventListener for the btnTimes
btnTimes.addEventListener('click', function (){
    if (btnHam.className !== "") {
        this.style.display = "none";
        btnHam.style.display = "block";
        navBar.classList.remove("show-nav");
    }
})