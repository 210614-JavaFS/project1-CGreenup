const URL = "http://localhost:8080/project1/"

function clearAll() {
  var div = document.getElementById('mainDiv');
  while(div.firstChild){
      div.removeChild(div.firstChild);
  }
}

let usernameField = document.getElementById('inputName');
let passwordField = document.getElementById('inputPassword')
let submitButton = document.getElementById('submitButton');

function attemptLogin(){

}