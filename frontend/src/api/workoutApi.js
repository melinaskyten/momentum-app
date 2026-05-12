import axios from 'axios'

const WORKOUT_URL = 'http://localhost:8081/workout'

const getToken = () => localStorage.getItem('token')

export const getWorkouts = async() => {
    const response =
        await axios.get(WORKOUT_URL,
            {headers: { Authorization: `Bearer ${getToken()}` }
            })
    return response.data
}

export const getWorkoutById = async(id) => {
    const response =
        await axios.get(`${WORKOUT_URL}/${id}`,
            {headers: {Authorization: `Bearer ${getToken()}`}
            })
    return response.data
}

export const createWorkout = async(workout) => {
    const response =
        await axios.get(WORKOUT_URL, workout,
            {headers: {Authorization: `Bearer ${getToken()}`}
            })
    return response.data
}

export const updateWorkout = async(id, workout) => {
    const response =
        await axios.put(`${WORKOUT_URL}/${id}`,
            {headers: {Authorization: `Bearer ${getToken()}`}
            })
    return response.data
}

export const deleteWorkout = async(id) => {
    const response =
        await axios.delete(`${WORKOUT_URL}/${id}`,
            {headers: {Authorization: `Bearer ${getToken()}`}
            })
}

export const searchExercises = async(query) => {
    const response =
        await axios.get(`http://localhost:8081/api/exercises/search?search=${query}`,
            {headers: {Authorization: `Bearer ${getToken()}`}
            })
    return response.data
}

export const getExerciseById = async(id) => {
    const response = await axios.get(`http://localhost:8081/api/exercises/${id}`,
        {
            headers: {Authorization: `Bearer ${getToken()}`}
        })

    return response.data
}