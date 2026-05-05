import axios from "axios";

const STATS_URL = 'http://localhost:8082/stats'

const getToken = () => localStorage.getItem('token')

export const getStats = async() => {
    const response =
        await axios.get(STATS_URL,
            {headers: { Authorization: `Bearer ${getToken()}` }
            })
    return response.data
}