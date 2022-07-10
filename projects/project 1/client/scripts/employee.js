document.getElementById('newTick').addEventListener('click', newTicket);
document.getElementById('pendTicks').addEventListener('click', pending);
document.getElementById('resoTicks').addEventListener('click', resolved);
document.getElementById('empProfile').addEventListener('click', profile);

let ticketUrl = "http://localhost:8080/projectOne/";
let divCon = document.getElementById('divList');
let ticketData;

let employee = sessionStorage.getItem('principal');
let empObj = JSON.parse(employee);

async function pending() {
    console.log(empObj.id);
    let response = await fetch(`${ticketUrl}tickets/emp&tickets=pending`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            "id": empObj.id
        })
    })

    if(response.status == 200) {
        ticketData = await response.json();
        addTickets(ticketData);
    }
}

async function resolved() {
    let response = await fetch(`${ticketUrl}tickets/emp&tickets=resolved`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            "id": empObj.id
        })
    })

    if(response.status == 200) {
        ticketData = await response.json();
        addTickets(ticketData);
    }
}

async function newTicket() {

}

function profile() {

}

function addTickets(data) {
    console.log(data);
    divCon.innerHTML = '';
    if(data.length > 1) {
        data.map(t => {
            let ticket = document.createElement('div');
            ticket.class = 'ticketDiv';
            ticket.innerHTML = `<p>Ticket ID: ${t.id}</p><p>Amount: ${t.amount}</p><p>Status: ${t.status}</p><p>Submitted: ${t.submitted}</p><hr>`;
            ticket.addEventListener('click', e => {e.data = t; addTickets(e.data)});
            divCon.append(ticket);
        });
    } else {
        let ticket = document.createElement('div');
        ticket.class = 'ticketDiv';
        if(data.id == undefined) {
            ticket.innerHTML = `<p>Ticket ID: ${data[0].id}</p><p>Amount: ${data[0].amount}</p><p>Description: ${data[0].description}</p><p>Type: ${data[0].type}</p><p>Status: ${data[0].status}</p><p>Approved By: ${data[0].approvedBy}</p><p>Submitted: ${data[0].submitted}</p><hr>`;
        } else {
            ticket.innerHTML = `<p>Ticket ID: ${data.id}</p><p>Amount: ${data.amount}</p><p>Description: ${data.description}</p><p>Type: ${data.type}</p><p>Status: ${data.status}</p><p>Approved By: ${data.approvedBy}</p><p>Submitted: ${data.submitted}</p><hr>`;
        }

        divCon.append(ticket);
    }

}
