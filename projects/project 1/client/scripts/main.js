let url = 'http://localhost:8080/projectOne/';
let button =  document.getElementById('loginButton');
if(button){
   if(sessionStorage.getItem("principal")) {
      button.innerText = `Logout`;
      button.addEventListener('click', logout);
   } else {
      button.addEventListener("click", ()=> window.location.href = './login.html')
   }
}

async function logout() {
   let response = await fetch(`${url}auth`, {
      method: 'DELETE',
      credentials: 'include'
   });

   if(response.status == 200) {
      sessionStorage.removeItem('principal');

      window.location.href = "./index.html"
   }
}