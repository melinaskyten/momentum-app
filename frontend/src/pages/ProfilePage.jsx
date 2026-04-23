import '../css/ProfilePage.css'
import StatCard from "../components/StatCard.jsx";

function ProfilePage() {
    const stats = [
        {id: 1, name: "Favourite exercise", value: "Bench press"},
        {id: 2, name: "Total weight lifted", value: "1000"},
        {id: 3, name: "Total workouts", value: "5"},
    ]

    return (
        <div>
            <div className="page-header">
                <h2 className="page-title">Profile</h2>
            </div>
            <div className="profile-card">
                <div className="profile-avatar">M</div>
                <div className="profile-info">
                    <p className="profile-email">Test@email.com</p>
                    <p className="profile-since">Member since April 2026</p>
                </div>
            </div>
            <div>
                <div className="page-header">
                    <h2 className="page-title">Statistics</h2>
                </div>
                <div className="stats-grid">
                    {stats.map((stat) => (
                        <StatCard stat={stat} key={stat.id}/>
                    ))}
                </div>
            </div>
        </div>

    )
}

export default ProfilePage