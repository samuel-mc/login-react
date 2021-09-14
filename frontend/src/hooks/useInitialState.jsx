import React from 'react';

const useInitalState = (token) => {
    const [user, setUser] = React.useState('');
    const [users, setUsers] = React.useState('');
    
    React.useEffect(() => {
        fetchUser();
        // fetchUsers();
    }, []);

    const fetchUser = async () => {
        await fetch(`http://localhost:8088/user?token=${token}`)
        .then(response => response.json())
        .then(data => setUser(data));
    }

    // const fetchUsers = async () => {
    //     await fetch(`http://localhost:8088/users`)
    //     .then(response => response.json())
    //     .then(data => setUsers(data));
    // }

    return {user, users};
}

export { useInitalState };

