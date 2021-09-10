import React from 'react';
import '../assets/style/LoginInput.css'

const LoginInput = (props) => {

    return (
        <input
            className="loginInput"
            name={props.name}
            placeholder={props.placeholder}
            type={props.type}
            onChange={props.onChange}
            required
        />
    );
}

export { LoginInput };