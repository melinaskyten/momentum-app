import '../css/ExerciseCard.css'

function ExerciseCard({ exercise }) {
    return (
        <div className="exercise-card">
            <h3 className="exercise-name">{exercise.name}</h3>
            <table className="sets-table">
                <thead>
                <tr>
                    <th>Set</th>
                    <th>Reps</th>
                    <th>Weight (kg)</th>
                </tr>
                </thead>
                <tbody>
                {exercise.sets.map((set, index) => (
                    <tr key={set.id}>
                        <td>{index + 1}</td>
                        <td>{set.reps}</td>
                        <td>{set.weight}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default ExerciseCard