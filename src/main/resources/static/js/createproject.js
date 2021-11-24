//Define variables
let name;
let description;
let communityImage;
//We assume that the user is a logged in user cookie must be present
let cookieArray =document.cookie.split(":")[2];
let userId = cookieArray.split(",")[0];