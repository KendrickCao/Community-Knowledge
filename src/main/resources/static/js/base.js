/*
javaScript: Menu bar activate and deactivate
 */

const btnHam = document.querySelector('.ham-btn');
const btnTimes = document.querySelector('.times-btn');
const navBar = document.getElementById('nav-bar');

// Code for show username and logout when user login
let accountNameElement = document.getElementById("accountName");
let cookieArrayForLog =document.cookie.split(":")[3]
let userName = cookieArrayForLog ? cookieArrayForLog.split("\"")[1]:null;
let loginElement = document.getElementById("fa-user-login");
let logoutElement = document.getElementById("fa-user-logout")
if (userName!=null) {
    accountNameElement.innerText = userName;
    loginElement.style.display = "none";
    logoutElement.style.display = "inline";
}
//logout function
logoutElement.onclick = function (){
    //clear all cookie
    let keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(let i = keys.length; i--;){
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
        }
    }
    //refresh this page
    location.reload();
}
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


