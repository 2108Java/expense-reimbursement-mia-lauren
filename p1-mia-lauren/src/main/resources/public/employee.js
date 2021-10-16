console.log("hello");

let ticketList = []; //store the tickets when they are first requested

window.addEventListener('load', getAllTickets()); //when the page loads start the process of populating the table

function Ticket(ticketId, username, expenseType, amount, description, dateSubmitted, status){

	this.ticketId = ticketId;
	this.username = username;
	this.expenseType = expenseType;
	this.amount = amount;
	this.description = description;
	this.dateSubmitted = dateSubmitted;
	this.status = status;	
}

function filterList(){
	let filter = document.getElementById("statuses").value; //where the user will set filter options
	resetTable(); //clear current table entries
	if(filter == "viewall"){
		for(let i = 0; i < ticketList.length; i++){
			addRow(ticketList[i]);
		}
	}//end viewall filter
	else if(filter == "pending"){
		for(let i = 0; i < ticketList.length; i++){
			if(ticketList[i].status == "PENDING"){
				addRow(ticketList[i]);
			}
		}
	}//end pending filter
	else if(filter == "approved"){
		for(let i = 0; i < ticketList.length; i++){
			if(ticketList[i].status == "APPROVED"){
				addRow(ticketList[i]);
			}
		}
	}//end approved filter
	else if(filter == "denied"){
		for(let i = 0; i < ticketList.length; i++){
			if(ticketList[i].status == "REJECTED"){
				addRow(ticketList[i]);
			}
		}
	}//end rejected filter
}

function getAllTickets(){
    let url = 'http://localhost:8000/tickets'; //endpoint where the request will be sent
    fetch(url)
    .then(response => response.json()) //turn the promise into json
    .then(data => data.forEach(function(receivedTicket){
	//manually map the json to tickets
	let ticket = new Ticket(receivedTicket.id,
	receivedTicket.username,
	receivedTicket.expenseType,
	receivedTicket.amount,
	receivedTicket.description,
	receivedTicket.submittedOn,
	receivedTicket.status);
	ticketList.push(ticket)//add the ticket to the ticketList
	addRow(ticket); //add the ticket to the table
	}))
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
	ticketIdColumn.innerText = ticket.ticketId;
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

function resetTable(){
	let table = document.getElementsByClassName("table")[0]; //get the table
	let currentTableBody = document.getElementById("ticketTableBody"); //get the table body
	currentTableBody.remove(); //clear the table
	
	table.createTBody().setAttribute("id", "ticketTableBody"); //replace the now deleted table body
}