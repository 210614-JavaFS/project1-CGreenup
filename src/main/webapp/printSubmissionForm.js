let mainDiv = document.getElementById('mainDiv');

//formDiv
let formDiv = document.createElement('div');
formDiv.id = 'formDiv'

//Form
let reimbForm = document.createElement('form');
reimbForm.id = 'signUpForm';
//reimbForm.action = 'new-request';
reimbForm.method = 'post';

//Select Label
let label = document.createElement('label');
label.innerHTML = 'Reimbursement Type';
reimbForm.appendChild(label);

//Select
let selectForm = document.createElement('select');
selectForm.id = 'selectForm';
selectForm.className = 'form-control';

//options
let allOptions = ['Moving', 'Parking', 'Commute', 'Business', 'Other'];
for(let option of allOptions){
    let opt = document.createElement('option');
    opt.value = option.toUpperCase();
    opt.innerHTML = option;
    selectForm.appendChild(opt);
}
formDiv.appendChild(selectForm);

//Dollar Amount label
label = document.createElement('label');
label.innerHTML = "Dollar amount for reimbursement";
formDiv.appendChild(label);

//Dollar amount input
let input = document.createElement('input');
input.id = 'dollarAmount';
input.type = 'text';
input.className = 'form-control';
input.placeholder = '000.00';
input.required = 'true';
formDiv.appendChild(input);

//Description Label
label = document.createElement('label');
label.innerHTML = 'A short description for the request';
formDiv.appendChild(label);

//Description input
input = document.createElement('input');
input.type = 'text';
input.id = 'description';
input.name = 'description';
input.className = 'form-control';
input.placeholder = 'Description';
input.required = 'true';
formDiv.appendChild(input);

//Whitespace - not as high as a line break
label = document.createElement('label');
formDiv.appendChild(label);

let button = document.createElement('button');
button.id = 'okButton';
button.className = 'btn form-control btn-secondary';
button.disabled = 'true';
button.type = 'submit';
button.innerHTML = 'Submit';
formDiv.appendChild(button);

function showForm(){
    mainDiv.appendChild(formDiv);
    input = document.createElement('input');
    input.value = user;
    input.disabled = 'true';
    reimbForm.appendChild(input);

}

function clearAll(username) {
    var div = document.getElementById('mainDiv');
    while(div.firstChild){
        div.removeChild(div.firstChild);
    }
    setUser(username);
    showForm();
}

const inputRegex = /^\d+(\.\d{1,2})?$/;
  const signUpForm = document.getElementById('signUpForm');
  const dollarAmount = document.getElementById('dollarAmount')
  const descriptionField = document.getElementById('description');
  const okButton = document.getElementById('okButton');
  let user;

  descriptionField.addEventListener('keyup', validateForm);
  dollarAmount.addEventListener('keyup', validateForm);
  
    function setUser(username){
        user = username;
    }

  function validateForm() {
    descriptionNotEmpty = descriptionField.checkValidity();
    dollarAmountIsValid = inputRegex.test(dollarAmount.value)

    if ( descriptionNotEmpty && dollarAmountIsValid) {
      okButton.disabled = false;
    } else {
      okButton.disabled = true;
    }
  }