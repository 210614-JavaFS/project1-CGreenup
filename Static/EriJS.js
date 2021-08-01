"use strict";

const URL = "http://localhost:8080/project1/"
let div = document.getElementById('mainDiv');

function clearAll() {
  while(div.firstChild){
      div.removeChild(div.firstChild);
  }
}

let iUsername;
let iPassword;

let usernameField = document.getElementById('inputName');
let usernameDangertext = document.getElementById('usernameError');
let passwordField = document.getElementById('inputPassword');
let passwordDangertext = document.getElementById('passwordError');
let button = document.getElementById('submitButton');

function getInputFields(){
  iUsername = usernameField.value;
  iPassword = passwordField.value;
  let user = {
    username: (iUsername? iUsername : ""),
    password: (iPassword? iPassword : "")
  };

  return user;
};
async function attemptLogin(){
  clearLoginForm();
  let input = getInputFields();
  let response = await fetch(URL + 'login', {
    method:'POST',
    body:JSON.stringify(input)
    //credentials: 'include' //Nevermind browsers say no, guess I won't do sessions :(
  });

  console.log(response.status);

  if(response.status === 201 || response.status===200){
    let data = await response.json();
    console.log(data);
    firstName = data.firstName;
    console.log(firstName);
  }

  if(response.status === 201){
    console.log("success, user is Employee");
    showEmployeeMenu(iUsername);

  }else if (response.status == 200) {
    console.log("success, user is Finance Manager")
    showManagerMenu(iUsername);
  }
  else if(response.status === 203){
    console.log("username incorrect");
    invalidate(usernameField);
    invalidate(passwordField);
    usernameDangertext.innerHTML = 'Username or Email does not exist';
  }
  else if (response.status === 204){
    console.log("username correct, password incorrect");
    invalidate(passwordField);
    passwordDangertext.innerHTML = 'Password does not match';
  }
  else{
    console.log("ERROR: I DON'T KNOW WHAT HAPPENED :(");
  }
  //clearAll();

};

button.onclick = attemptLogin;

function clearLoginForm(){
  usernameField.className = 'form-control';
  passwordField.className = 'form-control';
  usernameDangertext.innerHTML = '';
  passwordDangertext.innerHTML = '';

}

function invalidate(inputField){
  inputField.className = 'form-control is-invalid'
}