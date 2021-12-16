//As you can see, configurations are set by a simple JS object passed to the create() method.
ClassicEditor
    .create( document.querySelector( '#aboutBody' ), {
        toolbar: [ 'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ],
        heading: {
            options: [
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' }
            ]
        }
    } )
    .catch( error => {
        console.log( error );
    } );



//Define variables for each fetch user's enquiry details
let adminAboutTitle;
let adminAboutBody;


/*

    DEFINE FUNCTION

 */

//Function to capture user input - title input
const captureAdminAboutTitleInput = (event) =>{
    if (event.target == null) {
        window.alert("You can't Submit an empty Title")
        adminAboutTitleInput.focus();
    }
    adminAboutTitle = event.target.value;
}

//Function to capture user input - body input
const captureAdminAboutBodyInput = (event) =>{
    if(event.target.value == null) {
        window.alert("You can't Submit an empty Body")
        adminAboutBodyInput.focus();
    }
    adminAboutBody = event.target.value;
}

//Function to save the editing in the backend
const saveSend = async (event) => {
    event.preventDefault();
    if (adminAboutTitle !=null && adminAboutBody !=null) {
        const adminObject = {
            aboutTitle: adminAboutTitle,
            aboutBody: adminAboutBody
        }

        const response = await fetch ("http://localhost:8081/api/new-AdminAbout", {
            method: "POST",
            headers: {
                "Content-type" : "Application/json"
            },
            body: JSON.stringify(adminObject)
        })
        if (response.status == "200") {
            const data = await response.json()
            window.alert("You have updated the About page")
            window.location.reload(); // It will remain in the same page after being sent
        } else {
            throw new Error("There is an issue that occurred when trying to update the About page");
        }
    } else {
        window.alert("Something is wrong. Try again later.")
    }
}


//Lets get all the elements from the html using their ID
const adminAboutTitleInput = document.getElementById("aboutTitle");
const adminAboutBodyInput = document.getElementById("aboutBody");
const saveSendButton = document.getElementById("submit");


//Attach event listeners
adminAboutTitleInput.addEventListener("change", captureAdminAboutTitleInput);
adminAboutBodyInput.addEventListener("change", captureAdminAboutBodyInput);
saveSendButton.addEventListener("click", saveSend);
