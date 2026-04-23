import {Link} from "react-router-dom";
import '../css/NavBar.css'

function NavBar() {
    return (
        <aside className="sidebar">
            <div className="sidebar-brand">Momentum</div>
            <nav className="sidebar-nav">
                <Link to="/workouts" className="sidebar-link">Workouts</Link>
                <Link to="/me" className="sidebar-link">Profile</Link>
            </nav>
            <button className="sidebar-logout">Logout</button>
        </aside>
    )
}

export default NavBar