import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import ExerciseCard from '../components/ExerciseCard.jsx'
import ExerciseSearchModal from '../components/ExerciseSearchModal.jsx'
import {createWorkout, getExerciseById} from '../api/workoutApi.js'
import '../css/ViewWorkoutPage.css'

function CreateWorkoutPage() {
    const navigate = useNavigate()
    const [exercises, setExercises] = useState([])
    const [showModal, setShowModal] = useState(false)

    async function onAddExercise(exercise) {
        const fullExercise = await getExerciseById(exercise.exerciseId)
        setExercises(prev => [...prev, {
            exerciseId: fullExercise.exerciseId,
            name: fullExercise.name,
            bodyParts: fullExercise.bodyParts,
            equipments: fullExercise.equipments,
            instructions: fullExercise.instructions,
            sets: [{reps: 0, weight: 0}]
        }])
        setShowModal(false)
    }

    function onAddSet(exerciseIndex) {
        setExercises(prev => prev.map((ex, i) =>
            i === exerciseIndex
                ? { ...ex, sets: [...ex.sets, { reps: 0, weight: 0}] }
                : ex
        ))
    }

    function onSetChange(exerciseIndex, setIndex, field, value) {
        setExercises(prev => prev.map((ex, i) =>
            i === exerciseIndex
                ? {
                    ...ex,
                    sets: ex.sets.map((set, j) =>
                        j === setIndex ? { ...set, [field]: field === 'weight'
                                ? parseFloat(value) || 0
                                : parseInt(value) || 0
                        } : set
                    )
                }
                : ex
        ))
    }

    function onRemoveSet(exerciseIndex, setIndex) {
        setExercises(prev => prev.map((ex, i) =>
            i === exerciseIndex
                ? { ...ex, sets: ex.sets.filter((_, j) => j !== setIndex) }
                : ex
        ))
    }

    function onRemoveExercise(exerciseIndex) {
        setExercises(prev => prev.filter((_, i) => i !== exerciseIndex))
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
                                onAddSet={() => onAddSet(exerciseIndex)}
                                onRemoveSet={(setIndex) => onRemoveSet(exerciseIndex, setIndex)}
                                onSetChange={(setIndex, field, value) =>
                                    onSetChange(exerciseIndex, setIndex, field, value)}
                                onRemoveExercise={() => onRemoveExercise(exerciseIndex)}
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