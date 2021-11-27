// Define Variables

let eventName = null;
let eventDate = null;
let eventAddress = null;
let eventContributors = null;
let eventDetails = null;
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray.split(",")[0];


// Function to capture user Inputs in input boxes.
const captureUserInput = function (e) {

    const userInput = e.target.value;
    const elementName = e.target.name;

    if (elementName === "name") {
        eventName = userInput;

    } else if (elementName === "date") {
        eventDate = userInput;

    } else if (elementName === "contributors") {
        eventContributors = userInput;

    } else if (elementName === "aboutSection") {
        eventDetails = userInput;

    } else if (elementName === "address") {
        eventAddress = userInput;
    }
};

// Function to post event and to store event in .
const postEvent = async function (e) {

    e.preventDefault();
    if (eventName !=null && eventDate !=null && eventAddress !=null && eventContributors !=null && eventDetails !=null) {
        const event = {
            name:eventName,
            date:eventDate,
            aboutSection:eventDetails,
            contributors:eventContributors,
            address:eventAddress
        }
        console.log(event);
        const response = await fetch("http://localhost:8081/api/add-event/userId/"+ userId,{
            method:"POST",
            headers:{
                "Content-type":"application/json"
            },
            body:JSON.stringify(event)
        })
        if (response.status =="200") {
            const data = await response.json()
            console.log(data);
        } else {
            window.alert("A problem has occurred. Please try again later.")
        }
    } else {
        window.alert("Please Ensure that you Have Completed all Required Fields.")
    }
}

// Capture DOM elements
const eventNameInput = document.getElementById("name");
const eventDateInput = document.getElementById("date");
const eventAddressInput = document.getElementById("address");
const eventContributorsInput = document.getElementById("contributors");
const eventDetailsInput = document.getElementById("aboutSection");
const createEventButton = document.getElementById("createEvent-button");

// Event Listeners
eventNameInput.addEventListener("change", captureUserInput);
eventDateInput.addEventListener("change", captureUserInput);
eventAddressInput.addEventListener("change", captureUserInput);
eventContributorsInput.addEventListener("change", captureUserInput);
eventDetailsInput.addEventListener("change", captureUserInput);
createEventButton.addEventListener("click", postEvent);