import React from 'react';
import { LogOutButton } from '../components/LogOutButton';
import { UserTable } from '../components/UsersTable';
import { useInitalState } from '../hooks/useInitialState';
import '../assets/style/Home.css';
// import { fetchUser } from 'react';

const token = document.cookie.split('=')[1];

const Home = () => {
    const [users, setUsers] = React.useState([]);
    
    document.title = "Home";

    const userInitial = useInitalState(token);
    const user = userInitial.user;

    React.useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        await fetch(`http://localhost:8088/users`, {
            method: 'GET', // or 'PUT'
            headers:{
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            }
        })
        .then(response => response.json())
        .then(data => setUsers(data));
    }

    return (
        <div className="homeDiv">
            <LogOutButton />
            <p id="title"> Hola, {user.name} </p>
            <UserTable>
                {
                    users.map(user => 
                        <tr key={user.email}>
                            <td>{user.name} {user.lastName}</td>
                            <td>{user.email}</td>
                            <td>{user.rol}</td>
                        </tr>
                    )
                }
            </UserTable>

        </div>
    );

}

export { Home };