import { useNavigate } from 'react-router-dom'
import '../css/WorkoutCard.css'

function WorkoutCard({workout}) {
    const navigate = useNavigate()

    function onViewClick(){
        navigate(`/workouts/${workout.id}`)
    }

    const previewExercises = workout.exercises?.slice(0, 2) || []

    return (
        <div className="workout-card">
            <div>
                <h3>{workout.date}</h3>
                <div className="workout-card-exercises">
                    {previewExercises.map((exercise, index) => (
                        <p key={index}>{exercise.name}</p>
                    ))}
                </div>
            </div>
            <div>
                <button className="view-btn" onClick={() => onViewClick()}>
                    View
                </button>

            </div>
        </div>
    )
}

export default WorkoutCard