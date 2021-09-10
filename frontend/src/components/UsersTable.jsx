import React from 'react';
import '../assets/style/UserTable.css'

const UserTable = (props) => {
    return (
        <table className="userTable">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Rol</th>
            </tr>
            {props.children}
        </table>
    )
}

export { UserTable }