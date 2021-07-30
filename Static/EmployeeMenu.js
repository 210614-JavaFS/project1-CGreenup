let username;
let firstName;

function showEmployeeMenu(){
    username = iUsername;
    iUsername = '';
    iPassword = '';
    console.log(username);

    clearAll();

    let logoutButton = document.createElement('button');
    logoutButton.className = 'btn btn-primary';
    logoutButton.id='logoutBtn';
    logoutButton.innerHTML = 'Sign Out';

    let logoutDiv = document.createElement('div');
    logoutDiv.align = 'right';
    logoutDiv.appendChild(logoutButton);

    div.appendChild(logoutDiv);

    let header = document.createElement('h3');
    let greeting = 'Welcome, ' + firstName + '.';
    header.innerHTML = greeting;

    div.appendChild(header);

    button = document.createElement('button');
    button.id = 'newReq';
    button.className = 'btn btn-outline-secondary form-control';
    button.onclick = newForm;
    button.innerHTML = 'Create New Request';

    div.appendChild(button);

    let tableDiv = document.createElement('div');
    tableDiv.id = 'tableDiv';

    header = document.createElement('h5');
    header.innerHTML = 'Submitted Requests';

    tableDiv.appendChild(header);

    let table = document.createElement('table');
    table.className="table";
    table.id = 'requestTable';

    let tableHead = document.createElement('thead');
    tableHead.id = "tableHead";
    tableHead.style = 'background: black; color:white;';

    let row = document.createElement('tr');
    let cell = document.createElement('th');
    cell.scope = 'col';
    cell.innerHTML= 'ID';
    row.appendChild(cell);

    cell = document.createElement('th');
    cell.scope = 'col';
    cell.innerHTML= 'Description';
    row.appendChild(cell);

    cell = document.createElement('th');
    cell.scope = 'col';
    cell.innerHTML= 'Amount';
    row.appendChild(cell);    

    cell = document.createElement('th');
    cell.scope = 'col';
    cell.innerHTML= 'Status';
    row.appendChild(cell);

    tableHead.appendChild(row);

    let tableBody = document.createElement('tbody');
    tableBody.id = 'tableBody';

    table.appendChild(tableHead);
    table.appendChild(tableBody);

    tableDiv.appendChild(table);
    div.appendChild(tableDiv);

    let thisScript = document.createElement('script');
    thisScript.src = 'printSubmissionForm.js';
    let body = document.getElementById('bodyId');
    body.appendChild(thisScript);

}

async function populateTable(){

}

function newForm(){
    clearAll();
    showForm();
}