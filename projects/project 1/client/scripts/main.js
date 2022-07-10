let url = 'http://localhost:8080/projectOne/auth';

if(sessionStorage.getItem("principal")) {
   let button =  document.getElementById('loginButton');
   button.innerHTML = `Logout`;
   button.addEventListener('click', logout);
} 

async function logout() {
   let response = await fetch(url, {
      method: 'DELETE',
      credentials: 'include'
   });

   if(response.status == 200) {
      sessionStorage.removeItem('principal');
      window.location.href = "./index.html"
   }
}