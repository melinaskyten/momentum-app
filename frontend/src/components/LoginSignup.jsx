import {useState} from "react";
import '../css/LoginSignup.css'

function LoginSignup() {
    const [isLogin, setIsLogin] = useState()

    return (
        <div className="auth-wrapper">
            <div className="auth-card">
                <h1 className="auth-brand">Momentum</h1>
                <h2 className="auth-title">{isLogin ? "Login" : "Signup"}</h2>
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