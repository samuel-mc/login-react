import React from 'react';
import '../assets/style/LoginInput.css'

const LoginInput = (props) => {
    return (
        <
            input
            type={props.type}
            placeholder={props.placeholder}
            className="loginInput"
        />
    );
}

export { LoginInput };