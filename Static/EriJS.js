

const URL = "http://localhost:8080/project1/"
let div = document.getElementById('mainDiv');

function clearAll() {
  while(div.firstChild){
      div.removeChild(div.firstChild);
  }
}

let username;
let password;

let usernameField = document.getElementById('inputName');
let passwordField = document.getElementById('inputPassword');
let button = document.getElementById('submitButton');

function getInputFields(){
  let inputUsername = usernameField.value;
  let inputPassword = passwordField.value;
  let user = {
    username: (inputUsername? inputUsername : ""),
    password: (inputPassword? inputPassword : "")
  };

  return user;
};
async function attemptLogin(){
  let input = getInputFields();
  let response = await fetch(URL + 'login', {
    method:'POST',
    body:JSON.stringify(input)
    //credentials: 'include' //Nevermind browsers say no, guess I won't do sessions :(
  });

  console.log(response.status);

  //clearAll();

};

button.onclick = attemptLogin;
