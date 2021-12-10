// Get Dom Elements
let progressBarElement = document.getElementById("progressbar");
let fundsRequiredElement = document.getElementById("fundsRequired").innerText;
let fundsCollectedElement = document.getElementById("fundsCollected").innerText;
fundsRequired = fundsRequiredElement.substring(15,fundsRequiredElement.length - 7);
fundsCollected =fundsCollectedElement.substring(16,fundsCollectedElement.length - 7);
let progressNum = (fundsCollected/fundsRequired).toFixed(4)*100
progressBarElement.style.width = progressNum +'%'
console.log(progressNum);
