URL = "http://localhost:8080/project1/";

//formDiv
let formDiv = document.createElement('div');
formDiv.id = 'formDiv'

//Form
let reimbForm = document.createElement('span');
reimbForm.id = 'signUpForm';


//Select Label
let label = document.createElement('label');
label.innerHTML = 'Reimbursement Type';
reimbForm.appendChild(label);

//Select
let selectForm = document.createElement('select');
selectForm.id = 'selectForm';
selectForm.name = 'selectForm';
selectForm.className = 'form-control';

//options
let allOptions = ['Moving', 'Parking', 'Commute', 'Business', 'Other'];
for(let option of allOptions){
    let opt = document.createElement('option');
    opt.value = option.toUpperCase();
    opt.innerHTML = option;
    selectForm.appendChild(opt);
}
reimbForm.appendChild(selectForm);

//Dollar Amount label
label = document.createElement('label');
label.innerHTML = 'Dollar amount for reimbursement';
reimbForm.appendChild(label);

//Dollar amount input
let input = document.createElement('input');
input.id = 'dollarAmount';
input.type = 'text';
input.name = 'dollarAmount';
input.className = 'form-control';
input.placeholder = '000.00';
input.required = 'true';
reimbForm.appendChild(input);

//Description Label
label = document.createElement('label');
label.innerHTML = 'A short description for the request';
reimbForm.appendChild(label);

//Description input
input = document.createElement('input');
input.type = 'text';
input.id = 'description';
input.name = 'description';
input.className = 'form-control';
input.placeholder = 'Description';
input.required = 'true';
reimbForm.appendChild(input);

label = document.createElement('label');
reimbForm.appendChild(label);

button = document.createElement('button');
button.id = 'okButton';
button.className = 'btn form-control btn-success';
button.disabled = 'true';
button.innerHTML = 'Submit';
button.onclick = submitForm;
reimbForm.appendChild(button);

function showForm(){
    setUser(username);
    let mainDiv = document.getElementById('mainDiv');
    input = document.createElement('input');
    input.value = user;
    input.name = 'username';
    input.type = 'hidden';
    reimbForm.appendChild(input);
    formDiv.appendChild(reimbForm);
    mainDiv.appendChild(formDiv);
    signUpForm = document.getElementById('signUpForm');
    dollarAmount = document.getElementById('dollarAmount');
    descriptionField = document.getElementById('description');
    okButton = document.getElementById('okButton');
    descriptionField.addEventListener('keyup', validateForm);
    dollarAmount.addEventListener('keyup', validateForm);
}



async function submitForm(){

    showEmployeeMenu();
    iUsername = username;
}