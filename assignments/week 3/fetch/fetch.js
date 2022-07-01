// document.getElementById('getData').onclick = getData;
document.getElementById('getData').addEventListener("click", getData);

/*
    - When button is clicked, send HTTP request to the API for a specific id

    -- get the id value from the input √
    -- send request to the PokeAPi
        - method: GET
        - headers: none
        - body: none
        - URL pokeapi.co/api/v2/pokemon/ + id from input
    - might have to convert JSON to JS object
    
    - Populate the data in the section
 */

let baseApiUrl = 'https://pokeapi.co/api/v2/pokemon/';

async function getData() {
    if(document.getElementById('pokeTable')) {
       let pTable = document.getElementById('pokeTable');
       let section = document.getElementById('data'); 
       section.removeChild(pTable);
    }

    console.log("Button was clicked");
    let id = document.getElementById("dataInput").value;
    console.log(`ID: ${id}`);

    let res = await fetch(`${baseApiUrl}${id}`);
    if(res.status >= 200 && res.status < 300) {
        
        let data = await res.json();

        populateData(data);

    } else {
        console.log("Invalid request");
    }
}

function populateData(response) {
    console.log(response);
    createTable(response);
}

function createTable (res) {
    // Gets the section to append to
    let section = document.getElementById('data');
    let table = document.createElement('table');
    table.id = 'pokeTable'
    let tHeads = ["Name", "Abilities", "Moves"];
    // Getting name, abilities, moves (only 5), sprite
    let headingRow = document.createElement('tr');
    tHeads.map(thead => {
        let tableHead = document.createElement("th");
        tableHead.innerHTML = thead;
        headingRow.appendChild(tableHead);
    })
    table.appendChild(headingRow);
    let tr = document.createElement("tr");
    let nameData = document.createElement("td");
    nameData.innerText = res.name;
    tr.appendChild(nameData);

    let td = document.createElement("td");
    td.innerText = res.abilities[0].ability.name;
    tr.appendChild(td);

    let moveTd = document.createElement('td');
    moveTd.innerText = res.moves[0].move.name;
    tr.appendChild(moveTd);

    table.appendChild(tr);
    section.appendChild(table);
}
