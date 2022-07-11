document.getElementById('newTick').addEventListener('click', newTicket);
document.getElementById('pendTicks').addEventListener('click', pending);
document.getElementById('resoTicks').addEventListener('click', resolved);
document.getElementById('empProfile').addEventListener('click', profile);


let divCon = document.getElementById('divList');
let ticketData;

let employee = sessionStorage.getItem('principal');
let empObj = JSON.parse(employee);

async function pending() {
    let response = await fetch(`${url}tickets/emp&tickets=pending`, {
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
    let response = await fetch(`${url}tickets/emp&tickets=resolved`, {
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
    divCon.innerHTML = '';
    // Show the employees details
    let employee = document.createElement("div");
    employee.id = 'empProfile';
    employee.innerHTML = `<p>Username: ${empObj.username}</p><p>First Name: ${empObj.firstName}</p><p>Last Name: ${empObj.lastName}</p><p>Email: ${empObj.email}</p>`;
    divCon.append(employee);
    let button = document.createElement("button");
    button.innerText = "Edit Profile";
    button.addEventListener("click", editProfile)
    divCon.append(button);
}

function editProfile() {
    console.log("Editting!")
    divCon.innerHTML = '';
    let categories  = ['Username', 'First Name', 'Last Name', 'Email'];

    categories.map(cat => {
        let input = document.createElement('input');
        let label = document.createElement('label');
        input.placeholder = cat;
        label.htmlFor = `emp${cat}`;
        label.innerText = cat;
        switch(cat) {
            case 'Username':
                input.value = empObj.username;
                input.id = `empUsername`;
            break;
            case "First Name":
                input.value = empObj.firstName;
                input.id = `empFirstName`;
            break;
            case "Last Name":
                input.value = empObj.lastName;
                input.id = `empLastName`;
            break;
            case "Email":
                input.value = empObj.email;
                input.id = `empEmail`;
                input.type = 'email';
            break;
        }
        divCon.append(label);
        divCon.append(input);
        divCon.append(document.createElement('br'));

    })
    let submit = document.createElement("button");
    submit.id = 'profileSubmit';
    submit.innerText = 'Submit'
    submit.addEventListener('click', e => {
        empObj.username = document.getElementById('empUsername').value;
        empObj.firstName = document.getElementById('empFirstName').value;
        empObj.lastName = document.getElementById('empLastName').value;
        empObj.email = document.getElementById('empEmail').value;
        e.data = empObj;
        updateProfile();
    })
    divCon.append(submit);
}

async function updateProfile() {
    console.log(empObj);
    let response = await fetch(`${url}employees/emp`, {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(empObj)
    })
    if(response.status == 202) {
        divCon.innerHTML = '';
        console.log('updated');
    } else {
        console.log("Not modified");
    }
}

function addTickets(data) {

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
