// Define Variables

let eventName = null;
let eventDate = null;
let eventAddress = null;
let eventContributors = null;
let eventDetails = null;
let eventImage = null;
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray.split(",")[0];
console.log("i am up")
console.log(userId)
//We assume that the user is a logged in user cookie must be present

// Function to capture user Inputs in input boxes.
const captureUserInput = function (e) {
    console.log(e)
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
            address:eventAddress,
            eventImage:eventImage
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

const captureImageUploaded = async (e) =>{
    const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
    const imageFile = e.target.files[0];
    //Step 1 update the message of the STATUS as uploading
    const loadingText = document.createTextNode("Uploading....")
    uploadedImageStatusElement.innerHTML="";
    uploadedImageStatusElement.append(loadingText);
    //make a fetch POST call to the api/upload-image
    const formObject = new FormData();
    formObject.append("image", imageFile);
    const response = await fetch(uploadImageBackEndUri, {
        method:"POST",
        headers:{
            'Accept': 'application/json'
        },
        body: formObject
    })
    if(response.status == "200") {
        //update the image upload status method
        const uploadSuccess = document.createTextNode(`${imageFile.name} has been uploaded successfully!`)
        uploadedImageStatusElement.innerHTML="";
        uploadedImageStatusElement.append(uploadSuccess);
        eventImage = imageFile.name;
    }else{
        const uploadError = document.createTextNode("Something went wrong. Please try again later")
        uploadedImageStatusElement.innerHTML="";
        uploadedImageStatusElement.append(uploadError);
    }
}

// Capture DOM elements
const eventNameInput = document.getElementById("name");
const eventDateInput = document.getElementById("date");
const eventAddressInput = document.getElementById("address");
const eventContributorsInput = document.getElementById("contributors");
const eventDetailsInput = document.getElementById("aboutSection");
const createEventButton = document.getElementById("createEvent-button");
const uploadImageElement = document.getElementById("uploadImage");
const uploadedImageStatusElement = document.getElementById("upload-image__status");

// Event Listeners
eventNameInput.addEventListener("change", captureUserInput);
eventDateInput.addEventListener("change", captureUserInput);
eventAddressInput.addEventListener("change", captureUserInput);
eventContributorsInput.addEventListener("change", captureUserInput);
eventDetailsInput.addEventListener("change", captureUserInput);
createEventButton.addEventListener("click", postEvent);
uploadImageElement.addEventListener("change",captureImageUploaded );