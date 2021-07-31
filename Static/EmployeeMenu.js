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

    putData();
}

async function putData(){
    let userObject = {
        username: username
    }

    let response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(userObject)
    })

    console.log(JSON.stringify(userObject));
    
    if(response.status === 200){
        let data = await response.json();

        console.log(data);
        populateTable(data);
    }else{
        console.log('This user has no previous submissions')
    }
}

function populateTable(data){
    let tbody = document.getElementById('tableBody');
    tbody.innerHTML = '';
    for(let i = 0; i < data.length ; i++){
        let req = data[i];
        let row = document.createElement('tr');

        //Fill in ID field
        let td = document.createElement('td');
        td.innerText = req.id;
        row.appendChild(td);

        console.log(req.id + " " + typeof req.id)

        //Fill in description using the TYPE and the Description String
        td = document.createElement('td');
        td.innerText = req.type + ": " + req.description;
        row.appendChild(td);

        //Fill in the amount requested
        td = document.createElement('td');
        td.innerText = "$" + req.amount;
        row.appendChild(td);
        
        //Fill in the status
        //Color the box depending on status
        td = document.createElement('td');
        let status = req.status
        td.innerText = status;
        switch(status){
            case "PENDING":
                td.className = "bg-warning";
                break;
            case "APPROVED":
                td.className = "bg-success";
                break;
            case "DENIED":
                td.className = "bg-danger";
                break;
        }
        row.appendChild(td);

        tbody.appendChild(row);

    }
}

function newForm(){
    clearAll();
    setupFormScript();
}