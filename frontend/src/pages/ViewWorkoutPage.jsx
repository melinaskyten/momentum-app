import {useParams} from 'react-router-dom'
import {useEffect, useState} from "react";
import ExerciseCard from "../components/ExerciseCard.jsx";
import '../css/ViewWorkoutPage.css'
import ExerciseSearchModal from "../components/ExerciseSearchModal.jsx";
import {getWorkoutById} from "../api/workoutApi.js";

function ViewWorkoutPage() {

    const {id} = useParams()
    const [workout, setWorkout] = useState(null)
    const [showModal, setShowModal] = useState(false)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        async function fetchWorkout() {
            try {
                const data = await getWorkoutById(id)
                setWorkout(data)
            } catch(err) {
                setError('Could not fetch workout')
            } finally {
                setLoading(false)
            }
        }
        fetchWorkout();
    }, [id]);

    function onAddExercise(exercise){
        alert("Adding exercise: " + exercise.name)
        setShowModal(false)
    }

    if (loading) return <p className='page-title'>Loading</p>
    if (error) return <p className='page-title'>{error}</p>

    return (
        <div className="page-wrapper">
            <div>
                <div className="page-header">
                    <h2 className="page-title">{workout.date?.split('T')[0]}</h2>
                    <button className="new-btn" onClick={() => setShowModal(true)}>
                        + Add exercise
                    </button>
                </div>
                <div className="exercise-grid-wrapper">
                    <div className="exercise-grid">
                        {workout.exercises.map(exercise => (
                            <ExerciseCard key={exercise.id} exercise={exercise}/>
                        ))}
                    </div>
                </div>
                <div className="save-footer">
                    <button className="save-workout-btn">Save</button>
                </div>
                {showModal && (
                    <ExerciseSearchModal
                        onClose={() => setShowModal(false)}
                        onAdd={onAddExercise}
                    />
                )}
            </div>
        </div>

    )
}

export default ViewWorkoutPage