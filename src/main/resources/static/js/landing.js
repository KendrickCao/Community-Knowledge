// Creating the slideShow effect
const myslide = document.querySelectorAll('.mySlider'),
    dot = document.querySelectorAll('.dot');
let counter = 1;
slidefun(counter);

let timer = setInterval(autoslide, 8000);
function autoslide() {
  counter += 1;
  slidefun(counter);
}
function plusSlides(n) {
  counter += n;
  slidefun(counter);
  resetTimer();
}
function currentSlide(n) {
  counter = n;
  slidefun(counter);
  resetTimer();
}
function resetTimer() {
  clearInterval(timer);
  timer = setInterval(autoslide, 8000);
}

function slidefun(n) {
  let i;
  for (i = 0;i<myslide.length;i++){
    myslide[i].style.display = "none"
  }
  for (i = 0;i<dot.length;i++){
    dot[i].classList.remove('active');
  }
  if (n > myslide.length){
    counter = 1;
  }
  if (n < 1){
    counter = myslide.length;
  }
  myslide[counter - 1].style.display = "black";
  dot[counter - 1].classList.add('active');
}




// Get the elements by class name.
let communityNameContainerElement = document.getElementsByClassName(
  "CompanyCurrentNewsHeadlines"
)[0];
let communityImageContainerElement = document.getElementsByClassName(
  "companyBannerSubContent"
)[0];
let projectNameContainerElement = document.getElementsByClassName(
  "projectNameContainer"
)[0];
let projectImageContainerElement = document.getElementsByClassName(
  "projectImageContainer"
)[0];
let eventNameContainerElement =
  document.getElementsByClassName("eventNameContainer")[0];
let eventImageContainerElement = document.getElementsByClassName(
  "eventImageContainer"
)[0];
let logoElement = document.getElementById("logo");
logoElement.onclick = function () {
  window.location.href = "/";
};
// On load fetch the communities and projects.
async function fetchAll() {
  const responseCommunity = await fetch(
    "http://localhost:8081/api/communities"
  );
  const dataCommunity = await responseCommunity.json();
  const maxLengthCommunity =
    dataCommunity.length < 4 ? dataCommunity.length : 4;
  const responseProject = await fetch("http://localhost:8081/api/projects");
  const dataProject = await responseProject.json();
  const maxLengthProject = dataProject.length < 4 ? dataProject.length : 4;
  const responseEvent = await fetch("http://localhost:8081/api/events");
  const dataEvent = await responseEvent.json();
  const maxLengthEvent = dataEvent.length < 4 ? dataEvent.length : 4;

  //   if (dataCommunity) {
  //     for (let i = 0; i < maxLengthCommunity; i++) {
  //       const a = document.createElement("a");
  //       a.innerText = dataCommunity[i].name;
  //       a.setAttribute("href", `http://localhost:8081/community/${dataCommunity[i].id}`);
  //       communityNameContainerElement.append(a);
  //       // Upload images
  //       const div = document.createElement("div");
  //       div.classList.add("importantTopic");
  //       const sourceString = `http://localhost:8081/uploads/${dataCommunity[i].communityImage}`;
  //       div.innerHTML = `<img style="{{object-fit: contain}}"   src=${sourceString} alt="Community image">`;
  //       communityImageContainerElement.append(div);
  //     }
  //   }

  if (dataProject) {
    for (let i = 0; i < maxLengthProject; i++) {
      const a = document.createElement("a");
      a.innerText = dataProject[i].name;
      a.setAttribute(
        "href",
        `http://localhost:8081/project/${dataProject[i].id}`
      );
      projectNameContainerElement.append(a);
      // Upload images
      const div = document.createElement("div");
      div.classList.add("importantTopicProject");
      const sourceString = `http://localhost:8081/uploads/${dataProject[i].projectCoverImage}`;
      div.innerHTML = `<img style="{{object-fit: contain}}"   src=${sourceString} alt="Project image">`;
      projectImageContainerElement.append(div);
    }
  }

  if (dataEvent) {
    for (let i = 0; i < maxLengthEvent; i++) {
      const a = document.createElement("a");
      a.innerText = dataEvent[i].name;
      a.setAttribute("href", `http://localhost:8081/event/${dataEvent[i].id}`);
      eventNameContainerElement.append(a);
      // Upload images
      const div = document.createElement("div");
      div.classList.add("importantTopicEvent");
      const sourceString = `http://localhost:8081/uploads/${dataEvent[i].eventImage}`;
      div.innerHTML = `<img style="{{object-fit: contain}}"   src=${sourceString} alt="Event image">`;
      eventImageContainerElement.append(div);
    }
  }
}
fetchAll().then();
