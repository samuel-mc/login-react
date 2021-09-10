import React from 'react';
import { LoginInput } from '../components/LoginInput';
import { LoginButton } from '../components/LoginButton';
import '../assets/style/LoginContainer.css';
import { fetchLogin } from '../assets/js/auth/login';
import { useHistory } from "react-router-dom";


const LoginContainer = () => {

    const history = useHistory();

    const [data, setData] = React.useState({
        email: '',
        password: ''
    });

    const onChange = (event) => {
        setData({
            ...data,
            [event.target.name] : event.target.value
        })
    }

    const loginUser = async (e) => {
        e.preventDefault();
        const response = await fetchLogin(data);
        if(response && response.status === 200) {
            const json = await response.json();
            document.cookie = `token=${json.token}`;
            history.push("/");
        } else {
            alert("Datos Incorrectos")
            return;
        }
    }

    return (
        <div className="loginContainer">
            <h1 className="loginTitle"> Login </h1>
            <form onSubmit={loginUser}>
                <LoginInput placeholder="Email" type="email" name="email" onChange={onChange}/>
                <LoginInput placeholder="Password" type="password" name="password" onChange={onChange} />
                <LoginButton />
            </form>
        </div>
    )
}

export { LoginContainer };