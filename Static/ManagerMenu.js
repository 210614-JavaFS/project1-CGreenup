function showManagerMenu(){
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
  
}

showManagerMenu();