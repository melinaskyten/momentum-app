import {useState} from "react";
import logo from '../assets/logo.png'
import '../css/LoginSignup.css'

function LoginSignup() {
    const [isLogin, setIsLogin] = useState(true)

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

                    <form className="auth-form">
                        <div className="form-group">
                            <label className="form-label">Email</label>
                            <input className="form-input" type="email" placeholder="email@address.com"/>
                        </div>
                        <div className="form-group">
                            <label className="form-label">Password</label>
                            <input className="form-input" type="password" placeholder="********"/>
                        </div>
                        <button type="submit" className="auth-submit-btn">
                            {isLogin ? 'Login' : 'Sign Up'}
                        </button>
                    </form>
                </div>
            </div>
    )
}

export default LoginSignup