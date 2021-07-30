const inputRegex = /^\d+(\.\d{1,2})?$/;

let user;
let signUpForm;
let dollarAmount;
let descriptionField;
let okButton;

function setupFormScript(){
  showForm();

  signUpForm = document.getElementById('signUpForm');
  dollarAmount = document.getElementById('dollarAmount')
  descriptionField = document.getElementById('description');
  okButton = document.getElementById('okButton');


  descriptionField.addEventListener('keyup', validateForm);
  dollarAmount.addEventListener('keyup', validateForm);

}
function setUser(usernameInput){
  user = usernameInput;
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