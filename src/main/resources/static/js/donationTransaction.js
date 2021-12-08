function togglePopup() {
    document.getElementById('popup-1').classList.toggle('active');
}

//Define variables for Fetch Payload
let donorName;
let donorEmail;
let amount;
let dateVariable;
let projectId;
let creditCard;
let cardCvc;
let cardYear;
let cardMonth;
let userId;

//Define functions
//Function to capture user input - NAME
const captureDonorNameInput = (event) =>{
    if(event.target.value.length >10 || event.target.value.length <3){
        window.alert("Name cannot be more than 10 or less than 3")
        donorNameInput.focus();
    }
    donorName = event.target.value
}

//Function to capture user input - EMAIL
const captureUserEmailInput = (event) =>{
    if(!event.target.value.includes("@")){
        window.alert("Invlid email")
        donorEmailInput.focus();
    }
    donorEmail = event.target.value;
}

//Function to capture user input - PROJECT ID
const captureProjectIdInput = (event) =>{
    projectId = event.target.value
}

//Function to capture user input - AMOUNT
const captureAmountInput = (event) =>{
    amount = event.target.value
}

//Function to capture user input - DATE
const captureDateInput = (event) =>{
    dateVariable = event.target.value
}
//Function to capture user input - CARD NUMBER
const captureCardNumberInput = (event) =>{
    creditCard = event.target.value;
}

//Function to capture user input - CARD MONTH
const captureCardMonthInput =  (event) =>{
    cardMonth = event.target.value;
}

//Function to capture user input - CARD CVC
const captureCardCvcInput = (event) =>{
    cardCvc = event.target.value;
}

//Function to capture user input - CARD YEAR
const captureCardYearInput = (event) =>{
    cardYear = event.target.value;
}

//Function to save the transaction to the backend
const saveTransaction = async (event) =>{
       event.preventDefault();
       const transactionObject = {
            donorName: donorName,
            donorEmail: donorEmail,
            amount:amount,
            date:dateVariable,
            projectId:projectId,
            creditCard:creditCard,
            cardCvc:cardCvc,
            cardYear:cardYear,
            cardMonth:cardMonth
       }
       const response = await fetch("http://localhost:8081/api/new-transaction", {
           method:"POST",
           headers:{
               "Content-type":"Application/json"
           },
           body: JSON.stringify(transactionObject)
       })
    console.log("response--->", response)
        if(response.status ==200){
            const data = await response.json();
            window.alert("Transaction completed. Redirecting...")
            window.location.href = "/"
        }else{
            window.alert("Something wrong. Try again later")
        }

}
//Lets get all the elements from HTML based on ID
const donorNameInput = document.getElementById("donerName");
const donorEmailInput = document.getElementById("donerEmail");
const projectIdInput = document.getElementById("projectId");
const amountInput = document.getElementById("amount");
const dateInput = document.getElementById("date");
const cardNumberInput = document.getElementById("creditCard")
const cardCvcInput = document.getElementById("cardCvc")
const cardMonthInput = document.getElementById("cardMonth")
const cardYearInput = document.getElementById("cardYear")
const saveTransactionButton = document.getElementById("createDonateButton")


//Attach event listeners
donorNameInput.addEventListener("change", captureDonorNameInput);
donorEmailInput.addEventListener("change", captureUserEmailInput);
projectIdInput.addEventListener("change", captureProjectIdInput);
amountInput.addEventListener("change", captureAmountInput);
dateInput.addEventListener("change", captureDateInput);
cardNumberInput.addEventListener("change", captureCardNumberInput);
cardCvcInput.addEventListener("change", captureCardCvcInput);
cardMonthInput.addEventListener("change", captureCardMonthInput);
cardYearInput.addEventListener("change", captureCardYearInput);
saveTransactionButton.addEventListener("click", saveTransaction)

