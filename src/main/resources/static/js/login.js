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
    //Based on name, assign values and do validation
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

// Function to get userObject and send to user_table database if user credentials are valid.
const createUser = async function (e) {
    e.preventDefault();
    if (userEmail !=null && userPassword !=null) {
        loginButton.disabled = true;
        //Create User Object
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
        if(response.status =="200"){
            const data = await response.json()
            //store the user details in a cookie or on local storage TODO
            //Application cookies inspect


            //Create a JS object lets say loggedinUser = {email:data.email}
            //JSON.stringify(the JS object)
            console.log(data);
        }else{
            window.alert("A problem has occurred. Please try again later.")
        }

    } else {
        window.alert("Invalid user credentials - Please try again.")
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