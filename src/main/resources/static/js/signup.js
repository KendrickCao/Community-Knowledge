//Define Variables

var userName = null;
var userEmail = null;
var userPassword = null;
var userConfirmPassword = null;

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
    } else if (elementName === "confirmPassword") {
        if (userInput.length > 7) {
            validated = true;
        } else {
            window.alert("\n Password needs to be at least 8 characters.")
        }
    }
    return validated;
};

// Function to Capture user Inputs in input boxes
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

// Function to assess if userPassword and userConfirmPassword match
const matchCheck = function (userPassword, userConfirmPassword){
    let matching = false;
    if (userPassword === userConfirmPassword){
        matching = true;
    } else {
        window.alert("\n Passwords do not match: Please try again.")
    }
    return matching;
}

// Function to create userObject and send to user_table database if user credentials are valid.
const createUser = async function  (e) {
    e.preventDefault();
    const matching = matchCheck(userPassword, userConfirmPassword);
    if (matching && userName != null && userEmail !=null && userPassword !=null) {
        window.alert("User has been successfully created.");
        createUserButton.disabled = true;
        //Create User Object
        const userObject = {
            name:userName,
            email:userEmail,
            password:userPassword
        }

        const response = await fetch("http://localhost:8081/api/add-user",{
            method:"POST",
            headers:{
                "Content-type":"application/json"
            },
            body:JSON.stringify(userObject)

        })
       if(response.status =="200"){
           const data = await response.json()
           console.log(data);
       }else{
           window.alert("A problem has occurred. Please try again later.")
       }

        // Redirect user to login page
        window.location.href="http://localhost:8081/Login"

    } else {
        window.alert("Invalid user credentials - Please try again.")
    }
};

// Capture DOM elements
const nameInput = document.getElementById("name");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirmPassword");
const createUserButton = document.getElementById("createUser-button");

// Event Listeners
nameInput.addEventListener("change", captureUserInput);
emailInput.addEventListener("change", captureUserInput);
passwordInput.addEventListener("change", captureUserInput);
confirmPasswordInput.addEventListener("change", captureUserInput);
createUserButton.addEventListener("click", createUser);
