import {useState} from "react";
import logo from '../assets/logo.png'
import '../css/LoginSignup.css'
import {useNavigate} from "react-router-dom";
import {useAuth} from "../context/AuthContext.jsx";
import { login as loginApi, register as registerApi } from '../api/authApi.js'

function LoginSignup() {
    const [isLogin, setIsLogin] = useState(true);
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')

    const { login: saveToken } = useAuth()
    const navigate = useNavigate()

    async function HandleSubmit (e){
        e.preventDefault()
        setError('')

        try {
            const data = isLogin
                ? await loginApi(email, password)
                : await registerApi(email, password)

            saveToken(data.token)
            navigate('/workouts')
        } catch (error) {
            setError('Wrong user details')
        }
    }


    return (
            <div className="login-bg">
                <div className="blur-ball ball-1"></div>
                <div className="blur-ball ball-2"></div>
                <div className="blur-ball ball-3"></div>
                <div className="auth-card">
                    <div className="auth-logo-wrapper">
                        <div className="auth-logo-circle">
                            <img src={logo} alt="Logo" className="auth-logo"/>
                        </div>
                    </div>
                    <h1 className="auth-brand">Momentum</h1>
                    <div className="auth-toggle">
                        <button
                            className={`toggle-btn ${isLogin ? 'active' : ''}`}
                            onClick={() => setIsLogin(true)}>
                            Login
                        </button>
                        <button
                            className={`toggle-btn ${!isLogin ? 'active' : ''}`}
                            onClick={() => setIsLogin(false)}>
                            Sign Up
                        </button>
                    </div>

                    <form className="auth-form" onSubmit={HandleSubmit}>
                        <div className="form-group">
                            <label className="form-label">Email</label>
                            <input className="form-input"
                                   type="email"
                                   placeholder="email@address.com"
                                   value={email}
                                   onChange={event => setEmail(event.target.value)}/>
                        </div>
                        <div className="form-group">
                            <label className="form-label">Password</label>
                            <input
                                className="form-input"
                                type="password"
                                placeholder="********"
                                value={password}
                                onChange={event => setPassword(event.target.value)}/>
                        </div>
                        {error && <p className="auth-error">{error}</p>}
                        <button type="submit" className="auth-submit-btn">
                            {isLogin ? 'Login' : 'Sign Up'}
                        </button>
                    </form>
                </div>
            </div>
    )
}

export default LoginSignup