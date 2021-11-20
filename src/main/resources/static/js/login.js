//Define Variables

var userEmail = null;
var userPassword = null;

// Function to validate the userInputs
const validate = function (elementName, userInput) {

    let validated = false;

    if (elementName === "email") {
        if (userInput.length > 6) {
            validated = true;
        } else {
            window.alert("\n Email needs to be at least 7 characters.")
        }
    } else if (elementName === "password") {
        if (userInput.length > 7) {
            validated = true;
        }
        else {
            window.alert("\n Password needs to be at least 8 characters.")
        }
    }
    return validated;
};

// Function to Capture user Inputs in input boxes
const captureUserInput = function (e) {

    const userInput = e.target.value;
    const elementName = e.target.name;

    if (elementName === "email") {
        const validated = validate(elementName, userInput);
        if (validated) {
            userEmail = userInput;
        }
    } else if (elementName === "password") {
        const validated = validate(elementName, userInput);
        if (validated) {
            userPassword = userInput;
        }
    }
};

// Function to set cookie to contain user information retrieved from user_table DB
const setLoginCookie = function (userInformation, days){

    var date = new Date();
    date.setTime(date.getTime() + (days*24*60*60*1000));
    var expires = "expires="+ date.toUTCString();
    document.cookie = userInformation + expires + ";path=/";
}

// Function to get userObject and send to user_table database if user credentials are valid.
const createUser = async function (e) {

    e.preventDefault();
    if (userEmail !=null && userPassword !=null) {
        loginButton.disabled = true;
        const userObject = {
            email:userEmail,
            password:userPassword
        }
        const response = await fetch("http://localhost:8081/api/login-user",{
            method:"POST",
            headers:{
                "Content-type":"application/json"
            },
            body:JSON.stringify(userObject)

        })
        if (response.status =="200") {
            const data = await response.json()
            var cookieInformation= {email:data.email,id:data.id,name:data.name};
            var userInformation = JSON.stringify(cookieInformation);
            setLoginCookie(userInformation, 1);
        } else {
            window.alert("A problem has occurred. Please try again later.")
        }
    } else {
        window.alert("A problem has occurred. Please try again later.")
    }
};

// Capture DOM elements
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const loginButton = document.getElementById("loginUser-button");

// Event Listeners
emailInput.addEventListener("change", captureUserInput);
passwordInput.addEventListener("change", captureUserInput);
loginButton.addEventListener("click", createUser);