import React from 'react';
import '../assets/style/LogOutButton.css'
import { useHistory } from "react-router-dom";

const LogOutButton = () => {

    const history = useHistory();
    
    const logOut = () => {
        document.cookie = "token=; max-age=0";
        history.push("/login");
    }

    return (
        <div className="buttonContainer" onClick={logOut}>
            <button className="logOutButton"> Cerrar Sesión </button>
        </div>
    )
}

export { LogOutButton }