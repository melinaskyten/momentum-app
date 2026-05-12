import '../css/ExerciseSearchModal.css'
import {useState} from "react";
import {getExerciseById, searchExercises} from "../api/workoutApi.js";

function ExerciseSearchModal({ onClose, onAdd }) {

    const [query, setQuery] = useState('')
    const [results, setResults] = useState([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState('')
    const [hasSearched, setHasSearched] = useState(false)
    const [expandedId, setExpandedId] = useState(null)
    const [expandedExercise, setExpandedExercise] = useState (null)

    async function handleSearch() {
        if (query.length < 2) {
            return
        }
        try {
            setLoading(true)
            setError('')
            setHasSearched(true)
            const data = await searchExercises(query)
            setResults(data)
        } catch (err) {
            setError('Could not fetch exercises')
        } finally {
            setLoading(false)
        }
    }

    async function handleInfoClick(exercise) {
        if (expandedExercise?.exerciseId === exercise.exerciseId) {
            setExpandedExercise(null)
            return
        }
        try {
            const fullExercise = await getExerciseById(exercise.exerciseId)
            setExpandedExercise(fullExercise)
        } catch (err) {
            setError('Could not fetch exercise details')
        }
    }

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
                    value={query}
                    onChange={e => setQuery(e.target.value)}
                    onKeyDown={e => e.key === 'Enter' && handleSearch()}
                />
                <button className="modal-search-btn" onClick={handleSearch}>
                    Search
                </button>
                <div className="modal-results">
                    {loading && <p className="modal-loading">Searching...</p>}
                    {error && <p className="modal-error">{error}...</p>}
                    {results.map(exercise => (
                        <div key={exercise.exerciseId} className="modal-result-item">
                            <div className="modal-result-item-row">
                                <span>{exercise.name}</span>
                                <div className="modal-result-actions">
                                    <button
                                        className="modal-info-btn"
                                        onClick={() => handleInfoClick(exercise)}>
                                        i
                                    </button>
                                    <button
                                        className="modal-add-btn"
                                        onClick={() => onAdd(exercise)}>
                                        + Add
                                    </button>
                                </div>
                            </div>
                            {expandedExercise?.exerciseId === exercise.exerciseId && (
                                <div className="modal-exercise-info">
                                    <img
                                        className="modal-exercise-gif"
                                        src={expandedExercise.gifUrl}
                                        alt={expandedExercise.name}
                                    />
                                    <p><strong>Body Parts:</strong> {expandedExercise.bodyParts?.join(', ')}</p>
                                    <p><strong>Equipment:</strong> {expandedExercise.equipments?.join(', ')}</p>
                                    <ul className="modal-instructions">
                                        {expandedExercise.instructions?.map((step, index) => (
                                            <li key={index}>{step}</li>
                                        ))}
                                    </ul>
                                </div>
                            )}
                        </div>
                    ))}
                    {!loading && hasSearched && query.length >= 2 && results.length === 0 && (
                        <p className="moadl-loading">No exercise found</p>
                    )}
                </div>
            </div>
        </div>
    )
}

export default ExerciseSearchModal