import WorkoutCard from "../components/WorkoutCard.jsx";
import '../css/WorkoutPage.css'
import {useEffect, useState} from "react";
import {getWorkouts} from "../api/workoutApi.js";

function WorkoutsPage() {

    function onNewClick(){
        alert("Clicked")
    }

    const [workouts, setWorkouts] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        async function fetchWorkouts() {
            try {
                const data = await getWorkouts()
                console.log('Workouts: ', data)
                setWorkouts(data)
            } catch(err) {
                setError('Could not fetch workouts')
            } finally {
                setLoading(false)
            }
        }
        fetchWorkouts();
    }, []);

    if (loading) return <p className='page-title'>Loading</p>
    if (error) return <p className='page-title'>{error}</p>

    return (
        <div>
            <div className="page-header">
                <h2 className="page-title">Workouts</h2>
                <button className="new-btn" onClick={onNewClick}>+ New workout</button>
            </div>
            <div className="workout-grid">
            {workouts.map((workout) => (
                    <WorkoutCard workout={workout} key={workout.id}/>
                ))}
            </div>
        </div>
    )
}

export default WorkoutsPage