import axios from 'axios'


const USER_API_URL = 'http://localhost:8081/api'


class UserDataService {


    retrieveAllUsers() {

        return axios.get(`${USER_API_URL}/users`);
    }


    retrieveUser(id) {

        return axios.get(`${USER_API_URL}/users/${id}`);
    }


    deleteUser(id) {

        return axios.delete(`${USER_API_URL}/users/${id}`);
    }


    updateUser(id, user) {

        return axios.put(`${USER_API_URL}/users/${id}`, user);
    }


    createUser(user) {

        return axios.post(`${USER_API_URL}/users`, user);
    }

    
}

export default new UserDataService()