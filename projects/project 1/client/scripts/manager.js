document.getElementById('pendTick').addEventListener("click", pending);
document.getElementById('resoTick').addEventListener("click", resolved);
document.getElementById('emps').addEventListener("click", employees);

let ticketUrl = "http://localhost:8080/projectOne/tickets";

let ticketData;
async function pending() {
    let response = await fetch(`${ticketUrl}/pending`, {
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
    let response = await fetch(`${ticketUrl}/resolved`, {
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
    let response = await fetch(url, {
        method: 'GET',
        credentials: 'include'
    });

    if(response.status == 200) {
        let emps = await response.json();
        // Do something with the data
    }
}

function addToPage(data) {
    let divCon = document.getElementById('ticketList');
    divCon.innerHTML= '';
    if(data.length > 1) {
        data.map(t => {
            let ticket = document.createElement('div');
            ticket.class = 'ticketDiv';
            ticket.innerHTML = `<p>Ticket ID: ${t.id}</p><p>Amount: ${t.amount}</p><p>Type: ${t.type}</p><p>Employee ID: ${t.author}</p><hr>`;
            ticket.addEventListener('click', e => {e.data = t; addToPage(e.data);});
            divCon.append(ticket);
        })
    } else {
        let ticket = document.createElement('div');
        ticket.class = 'ticketDiv';
        ticket.innerHTML = `<p>Ticket ID: ${data.id}</p><p>Amount: ${data.amount}</p><p>Type: ${data.type}</p><p>Employee ID: ${data.author}</p><p>Description: ${data.description}</p><p>Status: ${data.status}</p><p>Submitted: ${data.submitted}</p><p>Approved By: ${data.approvedBy}</p>`;
        divCon.append(ticket);
        if(data.status == 'PENDING') {
            let aButton = document.createElement('button');
            aButton.innerText = 'Approve';
            aButton.addEventListener('click', e => {updateTicket(data, "APPROVED")});
            let dButton = document.createElement('button');
            dButton.innerText = 'Reject'
            dButton.addEventListener('click', e => {updateTicket(data, "REJECTED")});
            divCon.append(aButton);
            divCon.append(dButton);
        }

    }

}

async function updateTicket(data, decision) {
    let manager = sessionStorage.getItem('principal');
    let obj = JSON.parse(manager);
    data.status = decision;
    data.approvedBy = obj.id;


    let response = await fetch(`${ticketUrl}`, {
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
