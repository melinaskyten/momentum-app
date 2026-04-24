import {BrowserRouter, Routes, Route, useLocation} from 'react-router-dom'
import LoginPage from './pages/LoginPage.jsx'
import WorkoutsPage from './pages/WorkoutsPage.jsx'
import ProfilePage from './pages/ProfilePage.jsx'
import ViewWorkoutPage from "./pages/ViewWorkoutPage.jsx"
import NavBar from "./components/NavBar.jsx";
import './App.css'

function Layout() {
    const location = useLocation()
    const isAuthPage = ['/login', '/register'].includes(location.pathname)

    return (
        <div className="app-layout">
            {!isAuthPage && <NavBar/>}
            <div className={isAuthPage ? "auth-content" : "main-content"}>
                <Routes>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/workouts" element={<WorkoutsPage/>}/>
                    <Route path="/me" element={<ProfilePage/>}/>
                    <Route path="/workouts/:id" element={<ViewWorkoutPage/>}/>
                </Routes>
            </div>
        </div>
    )
}


function App() {
    return (
        <BrowserRouter>
            <Layout/>
        </BrowserRouter>

    )
}

export default App