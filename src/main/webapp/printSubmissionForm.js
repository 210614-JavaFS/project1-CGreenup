let mainDiv = document.getElementById('mainDiv');

//formDiv
let formDiv = document.createElement('div');
formDiv.id = 'formDiv'

//Form
let reimbForm = document.createElement('form');
reimbForm.id = 'signUpForm';
reimbForm.action = 'new-request';
reimbForm.method = 'post';

//Select Label
let label = document.createElement('label');
label.innerHTML = 'Reimbursement Type';
formDiv.appendChild(label);

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
formDiv.appendChild(input);

//Description Label
label = document.createElement('label');
label.innerHTML = 'A short description for the request';
formDiv.appendChild(label);



mainDiv.appendChild(formDiv);