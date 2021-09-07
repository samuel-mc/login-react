import React from 'react';
import { LoginInput } from '../components/LoginInput';
import { LoginButton } from '../components/LoginButton';
import '../assets/style/LoginContainer.css'

const LoginContainer = ({ children }) => {
    return (
        <div className="loginContainer">
            <h1 className="loginTitle"> Login </h1>
            <form action="">
                <LoginInput placeholder="Email" type="email" />
                <LoginInput placeholder="Password" type="pasword" />
                <LoginButton />
            </form>
        </div>
    )
}

export { LoginContainer };