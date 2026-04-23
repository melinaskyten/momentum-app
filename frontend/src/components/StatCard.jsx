import '../css/StatCard.css'

function StatCard({stat}) {


    return (
        <div className="stat-card">
            <h4>{stat.name}</h4>
            <p>{stat.value}</p>
        </div>
    )
}

export default StatCard