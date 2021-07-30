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