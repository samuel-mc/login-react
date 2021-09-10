// const API_URL ='http://localhost:8088/'

// const fetchUser = async () => {
//     const token = document.cookie.split('=')[1];

//     if (!token ){
//         return;
//     }

//     const requestOptions = {
//         method: "GET",
//         headers: {
//             "Content-Type": "application/json"
//         },
//     };
//     const response = await fetch(`${API_URL}users?token=${token}`, requestOptions);
//     // const json = await response.json();
//     console.log(response);
//     return response;
// }

// export { fetchUser };