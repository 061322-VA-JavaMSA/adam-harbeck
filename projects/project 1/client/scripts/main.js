if(sessionStorage.getItem("principal")) {
   let button =  document.getElementById('indexLogin');
   button.innerHTML = `<a href="./index.html">Logout</a>`;
   button.addEventListener('click', e => {
    sessionStorage.removeItem("principal");
   })
} 