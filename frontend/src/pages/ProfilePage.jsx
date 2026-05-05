import '../css/ProfilePage.css'
import {useEffect, useState} from "react";
import {getStats} from "../api/statsApi.js";
import StatCard from "../components/StatCard.jsx";
import {getUserDetails} from "../api/authApi.js";

function ProfilePage() {
    const [stats, setStats] = useState(null)
    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        async function fetchData() {
            try {
                const [statsData, userData] = await Promise.all([
                    getStats(),
                    getUserDetails()
                ])
                setStats(statsData)
                setUser(userData)
            } catch(err) {
                setError('Could not fetch data')
            } finally {
                setLoading(false)
            }
        }
        fetchData();
    }, []);

    if (loading) return <p className='page-title'>Loading</p>
    if (error) return <p className='page-title'>{error}</p>

    const statCards = [
        { id: 1, name: "Favourite exercise", value: stats.favoriteExercise },
        { id: 2, name: "Total weight lifted", value: `${stats.totalWeightLifted} kg` },
        { id: 3, name: "Total workouts", value: stats.totalWorkouts },
    ]

    return (
        <div>
            <div className="page-header">
                <h2 className="page-title">Profile</h2>
            </div>
            <div className="profile-card">
                <div className="profile-avatar">{user?.email.charAt(0)}</div>
                <div className="profile-info">
                    <p className="profile-email">{user?.email}</p>
                    <p className="profile-since">Member since {new Date(user?.createdAt)
                        .toLocaleDateString('sv-SE', { day: 'numeric', year: 'numeric', month: 'long'})}</p>
                </div>
            </div>
            <div>
                <div className="page-header">
                    <h2 className="page-title">Statistics</h2>
                </div>
                <div className="stats-grid">
                    {statCards.map((stat) => (
                        <StatCard stat={stat} key={stat.id}/>
                    ))}
                </div>
            </div>
        </div>

    )
}

export default ProfilePage