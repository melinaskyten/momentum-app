import {Link, useNavigate} from "react-router-dom";
import '../css/NavBar.css'
import {useAuth} from "../context/AuthContext.jsx";

function NavBar() {

    const {logout} = useAuth()
    const navigate = useNavigate()

    function onLogout(){
        logout()
        navigate('/login')
    }

    return (
        <aside className="sidebar">
            <div className="sidebar-brand">Momentum</div>
            <nav className="sidebar-nav">
                <Link to="/workouts" className="sidebar-link">Workouts</Link>
                <Link to="/me" className="sidebar-link">Profile</Link>
            </nav>
            <button className="sidebar-logout" onClick={onLogout}>Logout</button>
        </aside>
    )
}

export default NavBar