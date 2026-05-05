import axios from 'axios'

const AUTH_URL = 'http://localhost:8080/auth'

const getToken = () => localStorage.getItem('token')

export const login = async (email, password) => {
    const response = await axios.post(`${AUTH_URL}/login`, { email, password })
    return response.data
}

export const register = async (email, password) => {
    const response = await axios.post(`${AUTH_URL}/register`, { email, password })
    return response.data
}

export const getUserDetails = async() => {
    const response =
        await axios.get(`${AUTH_URL}/me`,
            {headers: { Authorization: `Bearer ${getToken()}` }
            })
    return response.data
}