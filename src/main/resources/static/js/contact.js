/* JS for the input effect when texting */

const inputs = document.querySelectorAll(".input");

function focusFunc(){
    let parent = this.parentNode;
    parent.classList.add("focus");
}

function blurFunc(){
    let parent = this.parentNode;
    if (this.value == ""){
    parent.classList.remove("focus");
    }
}


inputs.forEach(input =>{
    input.addEventListener("focus", focusFunc);
    input.addEventListener("blur", blurFunc);
})

//Define variables for fetching user's enquiry details
let contactName;
let contact;
