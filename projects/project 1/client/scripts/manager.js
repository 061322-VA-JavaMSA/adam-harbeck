document.getElementById('pendTick').addEventListener("click", pending);
document.getElementById('resoTick').addEventListener("click", resolved);
document.getElementById('emps').addEventListener("click", employees);

let manager = sessionStorage.getItem('principal');
let obj = JSON.parse(manager);

let heading = document.querySelector('h1');
heading.innerText = `Welcome ${obj.firstName}`;

let divCon = document.getElementById('divList');
let ticketData;
async function pending() {
    let response = await fetch(`${url}tickets/pending`, {
        method: "GET",
        credentials: 'include'
    });

    if(response.status == 200) {
        ticketData = await response.json();
        // Do something with the data
        addToPage(ticketData );

    }
}

async function resolved() {
    let response = await fetch(`${url}tickets/resolved`, {
        method: 'GET',
        credentials: 'include'
    });

    if(response.status == 200) {
        ticketData  = await response.json();
        // Do something with the data
        addToPage(ticketData );
    }
}

async function employees() {
    let response = await fetch(`${url}employees`, {
        method: 'GET',
        credentials: 'include'
    });

    if(response.status == 200) {
        let emps = await response.json();
        // Do something with the data
        divCon.innerHTML = '';
        emps.map(emp => {
            let employee = document.createElement('div');
            employee.className = 'ticketDiv';
            employee.innerHTML = `<p>Name: ${emp.firstName} ${emp.lastName}</p><p>Username: ${emp.username}</p><p>Email: ${emp.email}</p><p>Role: ${emp.role}</p>`;
            employee.addEventListener('click', e => {e.data = emp; getEmployeeTickets(e.data)});
            divCon.append(employee);
        })

    }
}

function addToPage(data) {
    divCon.innerHTML= '';
    if(data.length > 1) {
        data.map(t => {
            divCon.style.justifyContent = 'space-between';
            let ticket = document.createElement('div');
            ticket.className = 'ticketDiv';
            ticket.innerHTML = `<p>Ticket ID: ${t.id}</p><p>Amount: ${t.amount}</p><p>Type: ${t.type}</p><p>Employee ID: ${t.author}</p>`;
            if(t.status == "PENDING") {
                ticket.addEventListener('click', e => {e.data = t; addToPage(e.data);});
            }
            divCon.append(ticket);
        })
    } else {
        let ticket = document.createElement('div');
        ticket.className = 'ticketDiv';
        divCon.style.justifyContent = 'center';
        if(data.id == undefined) {
            ticket.innerHTML = `<p>Ticket ID: ${data[0].id}</p><p>Amount: ${data[0].amount}</p><p>Type: ${data[0].type}</p><p>Employee ID: ${data[0].author}</p><p>Description: ${data[0].description}</p><p>Status: ${data[0].status}</p><p>Submitted: ${data[0].submitted}</p><p>Approved By: ${data[0].approvedBy}</p>`;
        } else {
            ticket.innerHTML = `<p>Ticket ID: ${data.id}</p><p>Amount: ${data.amount}</p><p>Type: ${data.type}</p><p>Employee ID: ${data.author}</p><p>Description: ${data.description}</p><p>Status: ${data.status}</p><p>Submitted: ${data.submitted}</p><p>Approved By: ${data.approvedBy}</p>`;
        }

        if(data.status == 'PENDING' || data[0].status === 'PENDING') {
            let contDiv = document.createElement('div');
            contDiv.id = 'contDiv';
            contDiv.append(ticket);
            let newDiv = document.createElement('div');
            newDiv.id = 'buttonDiv';
            let aButton = document.createElement('button');
            aButton.innerText = 'Approve';
            aButton.addEventListener('click', e => {updateTicket(data, "APPROVED")});
            let dButton = document.createElement('button');
            dButton.innerText = 'Reject'
            dButton.addEventListener('click', e => {updateTicket(data, "REJECTED")});
            newDiv.append(aButton);
            newDiv.append(dButton);
            contDiv.append(newDiv);
            divCon.append(contDiv)
        }

    }

}

async function updateTicket(data, decision) {

    data.status = decision;
    data.approvedBy = obj.id;


    let response = await fetch(`${url}tickets`, {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    if(response.status == 202) {
        console.log('updated');
    }else {
        console.log("Not Modified");
    }
}

async function getEmployeeTickets(data) {
    let response = await fetch(`${url}tickets/emp&tickets`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            "id": data.id
        })
     })
    
    if(response.status == 200) {
        ticketData = await response.json();
        addToPage(ticketData);
    }
}
