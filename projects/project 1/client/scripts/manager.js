document.getElementById('pendTick').addEventListener("click", pending);
document.getElementById('resoTick').addEventListener("click", resolved);
document.getElementById('emps').addEventListener("click", employees);

let ticketUrl = "http://localhost:8080/projectOne/tickets";

async function pending() {
    let response = await fetch(`${ticketUrl}/pending`, {
        method: "GET",
        credentials: 'include'
    });

    if(response.status == 200) {
        let pendTicks = await response.json();
        // Do something with the data
        addToPage(pendTicks);

    }
}

async function resolved() {
    let response = await fetch(`${ticketUrl}/resolved`, {
        method: 'GET',
        credentials: 'include'
    });

    if(response.status == 200) {
        let resoTicks = await response.json();
        // Do something with the data
        addToPage(resoTicks);
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
    data.map(t => {
        let ticket = document.createElement('div');
        ticket.class = 'ticketDiv';
        console.log(t);
        ticket.innerHTML = `<p>Ticket ID: ${t.id}</p><p>Amount: ${t.amount}</p><p>Type: ${t.type}</p><p>Employee ID: ${t.author}</p><hr>`;
        divCon.append(ticket);
    })
}