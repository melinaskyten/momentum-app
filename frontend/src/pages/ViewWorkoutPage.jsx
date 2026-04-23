import {useParams} from 'react-router-dom'
import { useState} from "react";
import ExerciseCard from "../components/ExerciseCard.jsx";
import '../css/ViewWorkoutPage.css'
import ExerciseSearchModal from "../components/ExerciseSearchModal.jsx";

const testData = {
    id: 1,
    date: '2026-04-21',
    exercises: [
        {
            id: 1,
            name: 'Bench Press',
            sets: [
                {id: 1, reps: 10, weight: 80},
                {id: 2, reps: 8, weight: 85},
                {id: 3, reps: 6, weight: 90}
            ]
        },
        {
            id: 2,
            name: 'Squat',
            sets: [
                {id: 1, reps: 10, weight: 100},
                {id: 2, reps: 8, weight: 105}
            ]
        },
        {
            id: 3,
            name: 'Deadlift',
            sets: [
                {id: 1, reps: 6, weight: 120},
                {id: 2, reps: 5, weight: 100}
            ]
        },
        {
            id: 4,
            name: 'Bulgarian Split Squats',
            sets: [
                {id: 1, reps: 11, weight: 60},
                {id: 2, reps: 9, weight: 55}
            ]
        },
        {
            id: 5,
            name: 'Calf raises',
            sets: [
                {id: 1, reps: 11, weight: 45},
                {id: 2, reps: 9, weight: 45},
                {id: 3, reps: 7, weight: 40}
            ]
        }
    ]
}

function ViewWorkoutPage() {

    const {id} = useParams()
    const [showModal, setShowModal] = useState(false)

    function onAddExercise(exercise){
        alert("Adding exercise: " + exercise.name)
        setShowModal(false)
    }

    return (
        <div className="page-wrapper">
            <div>
                <div className="page-header">
                    <h2 className="page-title">{testData.date}</h2>
                    <button className="new-btn" onClick={() => setShowModal(true)}>
                        + Add exercise
                    </button>
                </div>
                <div className="exercise-grid-wrapper">
                    <div className="exercise-grid">
                        {testData.exercises.map(exercise => (
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