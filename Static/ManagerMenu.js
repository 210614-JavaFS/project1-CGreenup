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
    filter.checked = true;
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
  putMData();
}


async function putMData(){
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

    let response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(pendingObject)
    })

//    console.log("This is the JSON object I got in return: " + JSON.stringify(userObject));
//    console.log("This is the Response object I created: " + response);
//    console.log("The status code is " + response.status);
    
    if(response.status === 200){
        pendingRequests = await response.json();
    }

    response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(approvedObject)
    });
    if(response.status === 200){
        approvedObject = await response.json();
    }

    response = await fetch(URL + 'requests', {
        method:'POST',
        body:JSON.stringify(deniedObject)
    });
    if(response.status === 200){
        approvedObject = await response.json();
    }

    console.log('Finished Async Manager thing');
}