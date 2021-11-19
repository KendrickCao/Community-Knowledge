//Define Variables

var userName = null;
var userEmail = null;
var userPassword = null;
var userConfirmPassword = null;

//Define Functions

//Function to validate the userInputs
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
    } else if (elementName === "confirmPassword") {
        if (userInput.length > 7) {
            validated = true;
        } else {
            window.alert("\n Password needs to be at least 8 characters.")
        }
    }
    return validated;
};

//Function to capture user Inputs in input boxes
const captureUserInput = function (e) {

    const userInput = e.target.value;
    const elementName = e.target.name;

    //Based on name, assign values and do validation
    if(elementName ==="name"){
        userName = userInput;
    }
    else if (elementName === "email") {
        const validated = validate(elementName, userInput);
        if (validated) {
            userEmail = userInput;
        }
    } else if (elementName === "password") {
        const validated = validate(elementName, userInput);
        if (validated) {
            userPassword = userInput;
        }
    } else if (elementName === "confirmPassword") {
        const validated = validate(elementName, userInput);
        if (validated) {
            userConfirmPassword = userInput;
        }
    }
};

// Validate if password and confirmPassword match
const matchCheck = function (userPassword, userConfirmPassword){
    let matching = false;
    if (userPassword === userConfirmPassword){
        matching = true;
    } else {
        window.alert("\n Passwords do not match: Please try again.")
    }
    return matching;
}

// Create User Object
const createUser = function () {
    const matching = matchCheck(userPassword, userConfirmPassword);
    //const validated = captureUserInput();
    if (matching) {
        window.alert("User has been successfully created");
        createUserButton.disabled = true;
        //Create User Object
        const userObject = {
            name:userName,
            email:userEmail,
            password:userPassword
        }
        localStorage.setItem("createUser", JSON.stringify(userObject))
        //Redirect user to login page
        window.location.href="http://localhost:8080/Login"
    }
};

// Capture DOM elements
const nameInput = document.getElementById("name");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirmPassword");
const createUserButton = document.getElementById("createUser-button");

// Add Event Listeners
nameInput.addEventListener("change", captureUserInput);
emailInput.addEventListener("change", captureUserInput);
passwordInput.addEventListener("change", captureUserInput);
confirmPasswordInput.addEventListener("change", captureUserInput);
createUserButton.addEventListener("click", createUser);
