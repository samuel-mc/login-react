import React from 'react';
import { LoginInput } from '../components/LoginInput';
import { LoginButton } from '../components/LoginButton';
import '../assets/style/LoginContainer.css'
import { validateEmail } from '../assets/js/validateEmail'

const LoginContainer = () => {

    const [data, setData] = React.useState({
        email: '',
        password: ''
    });

    const handleInputChange = (event) => {
        setData({
            ...data,
            [event.target.name] : event.target.value
        })
        console.log(data)

    }

    const loginUser = (e) => {
        e.preventDefault();
        if(!validateEmail(data.email)) {
            alert('Email no valido.')
            return;
        }
        console.log('x');
    }

    return (
        <div className="loginContainer">
            <h1 className="loginTitle"> Login </h1>
            <form onSubmit={loginUser}>
                <LoginInput placeholder="Email" type="email" name="email" onChange={handleInputChange}/>
                <LoginInput placeholder="Password" type="password" name="password" onChange={handleInputChange} />
                <LoginButton />
            </form>
        </div>
    )
}

export { LoginContainer };