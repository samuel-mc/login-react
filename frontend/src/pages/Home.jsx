import React from 'react';
import '../assets/style/Home.css';
import { LogOutButton } from '../components/LogOutButton';
// import { fetchUser } from 'react';

const token = document.cookie.split('=')[1]; 

const Home = () => {
    document.title = "Home";
    const [user, setUser] = React.useState('');
    const [users, setUsers] = React.useState([]);

    React.useEffect(() => {
        fetch(`http://localhost:8088/user?token=${token}`)
        .then(response => response.json())
        .then(data => setUser(data));
    });


    return (
        <div className="homeDiv">
            <LogOutButton />
            <p id="title"> Hola, {user.name} </p>
        </div>
    );

}

export { Home };