const btnHam = document.querySelector('.hamBtn');
const btnTimes = document.querySelector('.timesBtn');
const navbar = document.getElementById('navBar');

btnHam.addEventListener('click', function(){
    if (btnHam.className !==""){
        btnHam.style.display = "none";
        btnTimes.style.display = "block";
        navbar.classList.add("show-nav");

    }
})
btnTimes.addEventListener('click', function(){
    if (btnTimes.className !=="") {
        this.style.display = "none";
        btnHam.style.display = "block";
        navbar.classList.remove("show-nav");
    }
})
/*
    creator: c21116175

    */