import '../css/ExerciseSearchModal.css'

function ExerciseSearchModal({ onClose, onAdd }) {

    const mockResults = [
        { id: 'abc123', name: 'Bench Press' },
        { id: 'def456', name: 'Squat' },
        { id: 'ghi789', name: 'Deadlift' },
    ]

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <div className="modal-header">
                    <h3 className="modal-title">Add Exercise</h3>
                    <button className="modal-close" onClick={onClose}>✕</button>
                </div>
                <input
                    className="modal-search"
                    type="text"
                    placeholder="Search exercises..."
                />
                <div className="modal-results">
                    {mockResults.map(exercise => (
                        <div key={exercise.id} className="modal-result-item">
                            <span>{exercise.name}</span>
                            <button
                                className="modal-add-btn"
                                onClick={() => onAdd(exercise)}>
                                + Add
                            </button>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default ExerciseSearchModal