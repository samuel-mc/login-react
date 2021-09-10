import React from 'react';
// import { fetchUser } from 'react';



const Home = () => {
    document.title = "Home";
    const token = document.cookie.split('=')[1]; 
    console.log(token);
    const [user, setUser] = React.useState('');

    React.useEffect(() => {
        // Actualiza el título del documento usando la API del navegador
        fetch(`http://localhost:8088/user?token=${token}`)
        .then(response => response.json())
        .then(data => console.log(data));
    });


    return (
        
        <p> Aqui va la pagina principal </p>
    );

}

export { Home };