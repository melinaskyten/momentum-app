import {useNavigate, useParams} from 'react-router-dom'
import {useEffect, useState} from "react";
import ExerciseCard from "../components/ExerciseCard.jsx";
import '../css/ViewWorkoutPage.css'
import ExerciseSearchModal from "../components/ExerciseSearchModal.jsx";
import {getWorkoutById, updateWorkout, getExerciseById} from "../api/workoutApi.js";

function ViewWorkoutPage() {

    const {id} = useParams()
    const [workout, setWorkout] = useState(null)
    const [showModal, setShowModal] = useState(false)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const navigate = useNavigate()

    useEffect(() => {
        async function fetchWorkout() {
            try {
                const data = await getWorkoutById(id)
                setWorkout(data)
            } catch (err) {
                setError('Could not fetch workout')
            } finally {
                setLoading(false)
            }
        }

        fetchWorkout();
    }, [id]);

    async function onAddExercise(exercise) {
        const fullExercise = await getExerciseById(exercise.exerciseId)
        setWorkout(prev => ({
            ...prev,
            exercises: [...prev.exercises, {
                exerciseId: fullExercise.exerciseId,
                name: fullExercise.name,
                bodyParts: fullExercise.bodyParts,
                equipments: fullExercise.equipments,
                instructions: fullExercise.instructions,
                sets: [{reps: 0, weight: 0}]
            }]
        }))
        setShowModal(false)
    }

    function onAddSet(exerciseIndex) {
        setWorkout(prev => ({
                ...prev,
                exercises: prev.exercises.map((ex, i) =>
                    i === exerciseIndex
                        ? {...ex, sets: [...ex.sets, {reps: 0, weight: 0}]}
                        : ex
                )
            }
        ))
    }

    function onRemoveSet(exerciseIndex, setIndex) {
        setWorkout(prev => ({
            ...prev,
            exercises: prev.exercises.map((ex, i) =>
                i === exerciseIndex
                    ? {...ex, sets: ex.sets.filter((_, j) => j !== setIndex)}
                    : ex
            )
        }))
    }

    function onRemoveExercise(exerciseIndex) {
        setWorkout(prev => ({
            ...prev,
            exercises: prev.exercises.filter((_, i) => i !== exerciseIndex)
        }))
    }


    function onSetChange(exerciseIndex, setIndex, field, value) {
        setWorkout(prev => ({
            ...prev,
            exercises: prev.exercises.map((ex, i) =>
                i === exerciseIndex
                    ? {
                        ...ex,
                        sets: ex.sets.map((set, j) =>
                            j === setIndex ? {
                                ...set, [field]: field === 'weight'
                                    ? parseFloat(value) || 0
                                    : parseInt(value) || 0
                            } : set
                        )
                    }
                    : ex
            )
        }))
    }

    async function onSave() {
        try {
            await updateWorkout(id, workout)
            navigate('/workouts')
        } catch (err) {
            setError('Could not save workout')
        }
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
                        {workout.exercises.map((exercise, exerciseIndex) => (
                            <ExerciseCard
                                key={exercise.id}
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

export default ViewWorkoutPage