import React from 'react';
import '../assets/style/UserTable.css'

const UserTable = (props) => {
    return (
        <table className="userTable">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody>
                {props.children}
            </tbody>
        </table>
    )
}

export { UserTable }