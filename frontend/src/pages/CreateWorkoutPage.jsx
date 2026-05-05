import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import ExerciseCard from '../components/ExerciseCard.jsx'
import ExerciseSearchModal from '../components/ExerciseSearchModal.jsx'
import { createWorkout } from '../api/workoutApi.js'
import '../css/ViewWorkoutPage.css'

function CreateWorkoutPage() {
    const navigate = useNavigate()
    const [exercises, setExercises] = useState([])
    const [showModal, setShowModal] = useState(false)

    function onAddExercise(exercise) {
        setExercises(prev => [...prev, {
            exerciseId: exercise.id,
            name: exercise.name,
            sets: [{ reps: '', weight: '' }]
        }])
        setShowModal(false)
    }

    function onAddSet(exerciseIndex) {
        setExercises(prev => prev.map((ex, i) =>
            i === exerciseIndex
                ? { ...ex, sets: [...ex.sets, { reps: '', weight: '' }] }
                : ex
        ))
    }

    function onSetChange(exerciseIndex, setIndex, field, value) {
        setExercises(prev => prev.map((ex, i) =>
            i === exerciseIndex
                ? {
                    ...ex,
                    sets: ex.sets.map((set, j) =>
                        j === setIndex ? { ...set, [field]: value } : set
                    )
                }
                : ex
        ))
    }

    async function onSave() {
        try {
            await createWorkout({ exercises })
            navigate('/workouts')
        } catch (err) {
            console.log(err)
        }
    }

    return (
        <div className="page-wrapper">
            <div>
                <div className="page-header">
                    <h2 className="page-title">New Workout - {new Date().toLocaleDateString(
                        'sv-SE', { day: 'numeric', year: 'numeric', month: 'long'})}</h2>
                    <button className="new-btn" onClick={() => setShowModal(true)}>
                        + Add exercise
                    </button>
                </div>
                <div className="exercise-grid-wrapper">
                    <div className="exercise-grid">
                        {exercises.map((exercise, exerciseIndex) => (
                            <ExerciseCard
                                key={exerciseIndex}
                                exercise={exercise}
                                editable={true}
                                onAddSet={() => onAddSet(exerciseIndex)}
                                onSetChange={(setIndex, field, value) =>
                                    onSetChange(exerciseIndex, setIndex, field, value)}
                            />
                        ))}
                    </div>
                </div>
            </div>
            <div className="save-footer">
                <button className="save-workout-btn" onClick={onSave}>Save</button>
            </div>
            {showModal && (
                <ExerciseSearchModal
                    onClose={() => setShowModal(false)}
                    onAdd={onAddExercise}
                />
            )}
        </div>
    )
}

export default CreateWorkoutPage