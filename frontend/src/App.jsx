import {BrowserRouter, Routes, Route} from 'react-router-dom'
import LoginPage from './pages/LoginPage.jsx'
import RegisterPage from './pages/RegisterPage.jsx'
import WorkoutsPage from './pages/WorkoutsPage.jsx'
import ProfilePage from './pages/ProfilePage.jsx'
import ViewWorkoutPage from "./pages/ViewWorkoutPage.jsx"
import NavBar from "./components/NavBar.jsx";
import './App.css'

function App() {
    return (
        <div className="app-layout">
            <BrowserRouter>
                <NavBar></NavBar>
                <div className="main-content">
                    <Routes>
                        <Route path="/login" element={<LoginPage/>}/>
                        <Route path="/register" element={<RegisterPage/>}/>
                        <Route path="/workouts" element={<WorkoutsPage/>}/>
                        <Route path="/me" element={<ProfilePage/>}/>
                        <Route path="/workouts/:id" element={<ViewWorkoutPage/>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>

    )
}

export default App