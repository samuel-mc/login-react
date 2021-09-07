import React from 'react';
import { LoginInput } from '../components/LoginInput';
import '../assets/style/LoginContainer.css'

const LoginContainer = ({ children }) => {
    return (
        <div className="loginContainer">
            <h1 className="loginTitle"> Login </h1>
            <form action="">
                <LoginInput placeholder="Email" type="email" />
                <LoginInput placeholder="Password" type="pasword" />
            </form>
        </div>
    )
}

export { LoginContainer };