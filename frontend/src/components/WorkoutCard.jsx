import { useNavigate } from 'react-router-dom'
import '../css/WorkoutCard.css'
import { Trash2 } from 'lucide-react';

function WorkoutCard({workout, onDelete}) {
    const navigate = useNavigate()

    const previewExercises = workout.exercises?.slice(0, 2) || []

    return (
        <div className="workout-card">
            <div>
                <h3>{workout.date?.split('T')[0]}</h3>
                <div className="workout-card-exercises">
                    {previewExercises.map((exercise, index) => (
                        <p key={index}>{exercise.name}</p>
                    ))}
                </div>
            </div>
            <div className="workout-card-actions">
                <button className="view-btn" onClick={() => navigate(`/workouts/${workout.id}`)}>
                    View
                </button>
                <button className="remove-exercise-btn" onClick={() => onDelete(workout.id)}>
                    <Trash2 size={20}/>
                </button>
            </div>
        </div>
    )
}

export default WorkoutCard