console.log("hello");

window.addEventListener('load', getAllTickets()); //when the page loads start the process of populating the table

function getAllTickets(){
    let url = 'http://localhost:8000/tickets'; //endpoint where the request will be sent
    fetch(url)
    .then(response => response.json()) //turn the promise into json
    .then(data => {
	data.forEach(function(ticket){ //iterate through the returned array
		addRow(ticket) //add each ticket to the table
	})
	})
}

function addRow(ticket){
	//append this to my table
	let tableBody = document.getElementById("ticketTableBody");
	
	//create a table row
	let tableRow = document.createElement("tr");
	
	//create columns
	let ticketIdColumn = document.createElement("td");
	let usernameColumn = document.createElement("td");
	let expenseTypeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descriptionColumn = document.createElement("td");
	let dateSubmittedColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
	
	//assign values to the columns
	ticketIdColumn.innerText = ticket.id;
	usernameColumn.innerText = ticket.username;
	expenseTypeColumn.innerText = ticket.expenseType;
	amountColumn.innerText = ticket.amount;
	descriptionColumn.innerText = ticket.description;
	dateSubmittedColumn.innerText = ticket.dateSubmitted;
	statusColumn.innerText = ticket.status;
	
	//add the columns to the row
	tableRow.appendChild(ticketIdColumn);
	tableRow.appendChild(usernameColumn);
	tableRow.appendChild(expenseTypeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descriptionColumn);
	tableRow.appendChild(dateSubmittedColumn);
	tableRow.appendChild(statusColumn);
	
	//add the row to the table
	tableBody.appendChild(tableRow);
	
};