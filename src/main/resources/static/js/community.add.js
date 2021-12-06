//Define variables
let name;
let description;
let communityImage;
//We assume that the user is a logged in user cookie must be present
let cookieArray = document.cookie.split(":")[2];
let userId = cookieArray ? cookieArray.split(",")[0] : null;
  if (!userId) {
    window.alert("You need to login first. Redirecting...");
    window.location.href = "/Login";
  }

//Define methods
//Method to capture user input
const captureUserInput = (e) => {
  const eventId = e.target.id;
  const eventValue = e.target.value;
  if (eventId === "community-name") {
    name = eventValue;
  } else if (eventId === "community-description") {
    description = eventValue;
  }
};

//Method to do the validations TODO

//Method to save the community to the backend
const saveCommunity = async (e) => {
  e.preventDefault();
  const url = `http://localhost:8081/api/add-community/${userId}`;
  const newCommunityObject = {
    name,
    description,
    communityImage,
  };
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newCommunityObject),
  });
  if (response.status == "200") {
    window.location.href = "/communities";
  } else {
    window.alert("Something went bad. Please try again later");
  }
};
const captureImageUploaded = async (e) => {
  const uploadImageBackEndUri = "http://localhost:8081/api/upload-image";
  const imageFile = e.target.files[0];
  //Step 1 update the message of the STATUS as uploading
  const loadingText = document.createTextNode("Uploading....");
  uploadedImageStatusElement.innerHTML = "";
  uploadedImageStatusElement.append(loadingText);
  //make a fetch POST call to the api/upload-image
  const formObject = new FormData();
  formObject.append("image", imageFile);
  const response = await fetch(uploadImageBackEndUri, {
    method: "POST",
    headers: {
      Accept: "application/json",
    },
    body: formObject,
  });
  if (response.status == "200") {
    //update the image upload status method
    const uploadSuccess = document.createTextNode(
      `${imageFile.name} has been uploaded successfully!`
    );
    uploadedImageStatusElement.innerHTML = "";
    uploadedImageStatusElement.append(uploadSuccess);
    communityImage = imageFile.name;
  } else {
    const uploadError = document.createTextNode(
      "Something went wrong. Please try again later"
    );
    uploadedImageStatusElement.innerHTML = "";
    uploadedImageStatusElement.append(uploadError);
  }
};

//Get the elements
const communityNameElement = document.getElementById("community-name");
const communityDescriptionElement = document.getElementById(
  "community-description"
);

//Save the community BUTTON
const addCommunityButton = document.getElementById("add-community_CTA");
//To get hold of the upload image elements
const uploadImageElement = document.getElementById("uploadImage-CTA");
const uploadedImageStatusElement = document.getElementById(
  "upload-image__status"
);

//Attach Event Listeners
communityNameElement.addEventListener("focusout", captureUserInput);
communityDescriptionElement.addEventListener("focusout", captureUserInput);

addCommunityButton.addEventListener("click", saveCommunity);
uploadImageElement.addEventListener("change", captureImageUploaded);
