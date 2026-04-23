import WorkoutCard from "../components/WorkoutCard.jsx";
import '../css/WorkoutPage.css'

function WorkoutsPage() {

    const workouts = [
        {id: 1, date: "2026-07-21"},
        {id: 2, date: "2026-04-03"},
        {id: 3, date: "2026-04-01"},
        {id: 4, date: "2026-03-20"},
        {id: 5, date: "2026-03-28"}
    ]

    function onNewClick(){
        alert("Clicked")
    }

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