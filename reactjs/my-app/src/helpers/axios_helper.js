import axios from 'axios';
import { getUser } from './auth_helper';


const _callApi = (token) => {
    const headers = {
        Accept: "application/json",
        Authorization: "Bearer " + token
    };

    //axios get console or response
    // return axios.get("http://localhost:8084/actuator", { headers }).then(response => {
    //     console.log(response);
    // return axios.get("http://localhost:8084/actuator", { headers, mode: 'cors' });
     axios.get("http://localhost:8084/v1/modulos/", { headers }).then(response => { console.log(response) });

     return "{ 'name': 'test' }";
    }

export const callApi = () => {
    return getUser().then(user => {
        if (user && user.access_token) {
            return _callApi(user.access_token).catch(error => {
                throw error;
            });
        } else {
            throw new Error('user is not logged in');
        }
    });
}
