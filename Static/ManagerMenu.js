let managerUsername;
let pendingRequests;
let approvedRequests;
let deniedRequests;

function showManagerMenu(username){
  clearAll();

  let logoutButton = document.createElement('button');
  logoutButton.className = 'btn btn-primary';
  logoutButton.id='logoutBtn';
  logoutButton.innerHTML = 'Sign Out';
  logoutButton.onclick = () =>{location.reload(true)}

  let logoutDiv = document.createElement('div');
  logoutDiv.align = 'right';
  logoutDiv.appendChild(logoutButton);

  div.appendChild(logoutDiv); 
  
  let header = document.createElement('h3');
  let greeting = 'Welcome, ' + firstName + '.';
  header.innerHTML = greeting;
  div.appendChild(header);

  let filterDiv = document.createElement('div');
  filterDiv.align = 'right';
  
  let filterOptions = ['Pending', 'Approved', 'Denied'];
  
  for(let i = 0; i< filterOptions.length; i++){
    let inlineDiv = document.createElement('div');
    inlineDiv.class = "form-check form-check-inline";
    
    let filterString = filterOptions[i];
    let filter = document.createElement('input');
    filter.className = 'form-check-input';
    filter.id = filterString.toLowerCase() + "Filter";
    filter.type = 'checkbox';
    if(filterOptions[i] === 'Pending'){
        filter.checked = true;
    }
    filter.value = true;
    filter.onchange = refreshTable;
    inlineDiv.appendChild(filter);
    
    let filterLabel = document.createElement('label');
    filterLabel.className = 'form-check-label';
    filterLabel.style = 'padding:0px'
    filterLabel.for = filterString.toLowerCase() + "Filter";
    filterLabel.innerHTML = filterString;
    inlineDiv.appendChild(filterLabel);
    
    filterDiv.appendChild(inlineDiv);
  }
  
  div.appendChild(filterDiv);
  let tableDiv = document.createElement('div');
  tableDiv.id = 'tableDiv';

  header = document.createElement('h5');
  header.innerHTML = 'Submitted Requests';

  tableDiv.appendChild(header);

  let table = document.createElement('table');
  table.className="table table-striped";
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
  cell.innerHTML= 'Author';
  row.appendChild(cell);    

  cell = document.createElement('th');
  cell.scope = 'col';
  cell.innerHTML= 'Approve/Deny';
  row.appendChild(cell);

  tableHead.appendChild(row);

  let tableBody = document.createElement('tbody');
  tableBody.id = 'tableBody';

  table.appendChild(tableHead);
  table.appendChild(tableBody);

  tableDiv.appendChild(table);
  div.appendChild(tableDiv);

  managerUsername = username;
  getData();
}


async function getData(){
//    console.log("I've begun putting data");    
    console.log("I've got the username: " + managerUsername);


    //I'm sorry this is hard-coded, I still have a bit to learn about javascript
    let pendingObject = {
        username: managerUsername,
        status: 'PENDING'
    }
    let approvedObject = {
        username: managerUsername,
        status: 'APPROVED'
    }
    let deniedObject = {
        username: managerUsername,
        status: 'DENIED'
    }

    console.log("I'm about to get data from the Java server");
    //    console.log("This is the JSON object I got in return: " + JSON.stringify(userObject));
    //    console.log("This is the Response object I created: " + response);
    //    console.log("The status code is " + response.status);

    //Get all of the pending requests
    let response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(pendingObject)
    })
    if(response.status === 200){
        pendingRequests = await response.json();
        console.log(pendingRequests);
    }

    //get all of the approved requests
    response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(approvedObject)
    });
    if(response.status === 200){
        approvedRequests = await response.json();
        console.log(approvedRequests);
    }

    //get all of the denied requests
    response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(deniedObject)
    });
    if(response.status === 200){
        deniedRequests = await response.json();
        console.log(deniedRequests);
    }
    
    console.log('Finished Async manager getData');
    refreshTable();
}

function populateRequestTable(data, typeString){
    console.log('populating the data for reimbursements of type: ' + typeString);

    let tableBody = document.getElementById('tableBody');

    for(let i = 0; i < data.length; i++){
        let req = data[i];
        let row = document.createElement('tr');

        //Fill in ID field
        let td = document.createElement('td');
        td.innerText = req.id;
        row.appendChild(td);

        //Fill in description using the TYPE and the Description String
        td = document.createElement('td');
        td.innerText = req.description;
        row.appendChild(td);

        //Fill in the amount requested
        td = document.createElement('td');
        td.innerText = "$" + req.amount;
        row.appendChild(td);

        //Fill in author field
        td = document.createElement('td');
        td.innerText = req.name;
        row.appendChild(td);

        //add the two buttons
        td = document.createElement('td');
        if(typeString === 'pending'){
            let button = document.createElement('button');
            button.className = 'btn btn-success';
            button.innerText = 'Approve';
            button.id = 'approveForm' + req.id;
            button.style = 'width:50%;';
            button.onclick = () => {return decideRequest('approve', req.id)};
   
            td.appendChild(button);

            button = document.createElement('button');
            button.className = 'btn btn-danger';
            button.innerText = 'Deny';
            button.id = 'denyForm' + req.id;
            button.style = 'width:50%;'
            button.onclick = () => {return decideRequest('deny', req.id)};

            td.appendChild(button);
        }
        else if (typeString ==='approved'){
            td.innerText = 'APPROVED';
            td.className = 'bg bg-success text-white';
        }
        else if (typeString ==='denied'){
            td.innerText = 'DENIED';
            td.className = 'bg bg-danger text-white';
        }

        row.appendChild(td);
        tableBody.appendChild(row);
    }
}

function refreshTable(){
    console.log("refreshing table");

    let tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = "";
    if(document.querySelector('#pendingFilter').checked){
        populateRequestTable(pendingRequests, 'pending');
    }
    if (document.querySelector('#approvedFilter').checked){
        populateRequestTable(approvedRequests, 'approved');
    }
    if(document.querySelector('#deniedFilter').checked){
        populateRequestTable(deniedRequests, 'denied')
    }    
}

async function decideRequest(userAction, requestId){
    let decision = {
        action: userAction,
        username: managerUsername,
        id: requestId
    }
    let response = await fetch(URL + "decide-request", {
        method:'POST',
        body: JSON.stringify(decision)
    });
    getData();
}