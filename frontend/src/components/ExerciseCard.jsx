import '../css/ExerciseCard.css'
import {Trash2} from 'lucide-react'

function ExerciseCard({exercise, onAddSet, onRemoveSet, onSetChange, onRemoveExercise}) {
    return (
        <div className="exercise-card">
            <div className="exercise-card-header">
                <h3 className="exercise-name">{exercise.name}</h3>
                <button className="remove-exercise-btn" onClick={onRemoveExercise}>
                    <Trash2 size={14}/>
                </button>
            </div>
            <table className="sets-table">
                <thead>
                <tr>
                    <th>Set</th>
                    <th>Reps</th>
                    <th>Weight (kg)</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {exercise.sets.map((set, index) => (
                    <tr key={set.id ?? index}>
                        <td> {index + 1}</td>
                        <td>
                            <input
                                type="number"
                                className="set-input"
                                value={set.reps}
                                onChange={e =>
                                    onSetChange(index, 'reps', e.target.value)}
                            />
                        </td>
                        <td>
                            <input
                                type="number"
                                className="set-input"
                                value={set.weight}
                                onChange={e =>
                                    onSetChange(index, 'weight', e.target.value)}
                            />
                        </td>
                        <td>
                            <button
                                className="remove-set-btn"
                                onClick={() => onRemoveSet(index)}>
                                <Trash2 size={14}/>
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button className="add-set-btn" onClick={onAddSet}>+ add set</button>
        </div>
    )
}

export default ExerciseCard