import { validateEmail } from '../validateEmail';

const API_URL ='http://localhost:8088/'

const fetchLogin = async (data) => {
    if(!validateEmail(data.email)) {
        alert('Email no valido.');
        return null;
    }
    if(!data.password){
        alert('Password no valido.');
        return null;
    }

    const bodyData = JSON.stringify({
        email: data.email,
        password: data.password,
    })

    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: bodyData
    };
    const response = await fetch(`${API_URL}signin`, requestOptions);
    return response;
}

export { fetchLogin };