

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
let passwordField = document.getElementById('inputPassword');
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

  if(response.status === 201){
    console.log("success, user is Employee");
    showEmployeeMenu(username);

  }else if (response.status == 200) {
    console.log("success, user is Finance Manager")
  }
  else if(response.status === 203){
    console.log("username incorrect");
    invalidate(usernameField);
  }
  else if (response.status === 204){
    console.log("username correct, password incorrect");
    invalidate(passwordField);
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
}

function invalidate(inputField){
  inputField.className = 'form-control is-invalid'
}