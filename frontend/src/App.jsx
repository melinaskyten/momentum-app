import {BrowserRouter, Routes, Route, useLocation, Navigate} from 'react-router-dom'
import {useAuth} from "./context/AuthContext.jsx";
import LoginPage from './pages/LoginPage.jsx'
import WorkoutsPage from './pages/WorkoutsPage.jsx'
import ProfilePage from './pages/ProfilePage.jsx'
import ViewWorkoutPage from "./pages/ViewWorkoutPage.jsx"
import NavBar from "./components/NavBar.jsx";
import CreateWorkoutPage from "./pages/CreateWorkoutPage.jsx"
import './App.css'

function ProtectedRoute ({children}) {
    const {token} = useAuth()
    if (!token) return <Navigate to="/login"/>
    return children
}

function Layout() {
    const location = useLocation()
    const isAuthPage = ['/login', '/register'].includes(location.pathname)

    return (
        <div className="app-layout">
            {!isAuthPage && <NavBar/>}
            <div className={isAuthPage ? "auth-content" : "main-content"}>
                <Routes>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/workouts" element={<ProtectedRoute><WorkoutsPage/></ProtectedRoute>}/>
                    <Route path="/me" element={<ProtectedRoute><ProfilePage/></ProtectedRoute>}/>
                    <Route path="/workouts/:id" element={<ProtectedRoute><ViewWorkoutPage/></ProtectedRoute>}/>
                    <Route path="/workouts/new" element={<ProtectedRoute><CreateWorkoutPage/></ProtectedRoute>}/>
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