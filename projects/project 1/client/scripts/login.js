if(sessionStorage.getItem("principal")) {
    window.location.href="./index.html"
}

document.getElementById('loginButton').addEventListener("click", login);
document.getElementById('loginPass').addEventListener('keydown', function(e) {
    if(e.key == 'Enter') {
        login();
    }
});

let url = 'http://localhost:8080/projectOne/auth';


async function login() {
    console.log("Login function");
    let username = document.getElementById('loginUser').value;
    let password = document.getElementById('loginPass').value;

    let response = await fetch(url, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'username': username,
            'password': password
        })
    });

    if(response.status == 200) {
        let data = await response.json();
        sessionStorage.setItem('principal', data);
        if(data.role === "MANAGER") {
            window.location.href = "/client/views/mDashboard.html";
        } else {
            window.location.href = "/client/views/eDashboard.html";
        }


    }

}