// On load fetch the communities
window.onload = async () => {
  // Get the elements by class name
  let communityNameContainerElement = document.getElementsByClassName(
    "CompanyCurrentNewsHeadlines"
  )[0];
  let communityImageContainerElement = document.getElementsByClassName(
    "companyBannerSubContent"
  )[0];
  const response = await fetch("http://localhost:8081/api/communities");
  const data = await response.json();
  if (data) {
    for (let i = 0; i < data.length; i++) {
      const a = document.createElement("a");
      a.innerText = data[i].name;
      a.setAttribute("href", `http://localhost:8081/community/${data[i].id}`);
      communityNameContainerElement.append(a);
      // Upload images
      const div = document.createElement("div");
      div.classList.add("importantTopic");
      const sourceString = `http://localhost:8081/uploads/${data[i].communityImage}`;
      div.innerHTML = `<img style="{{object-fit: contain}}"   src=${sourceString} alt="Commutiy image">`;
      communityImageContainerElement.append(div);
    }
  }
};
