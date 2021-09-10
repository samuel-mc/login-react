import React from 'react';
import '../assets/style/LogOutButton.css'

const LogOutButton = () => {

    const logOut = () => {
        document.cookie = "token=; max-age=0";
    }

    return (
        <div className="buttonContainer" onClick={logOut}>
            <button className="logOutButton"> Cerrar Sesión </button>
        </div>
    )
}

export { LogOutButton }