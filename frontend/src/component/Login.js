import React, {Component} from "react";
import NavBar from "./NavBar";
import LoginForm from "./LoginForm";
import "../static/css/userlogin.css";

class Login extends Component {

    render() {
        return (

            <div className="innerHTML-container">
                <NavBar />
                <main>
                    <div className="main-container">
                        <div className="main-content">
                            <div className="logo-container">
                                <img src="./img/Safeimagekit-resized-img.png" alt="Logo" className="logo-big"/>
                            </div>
                            <div className="input-container">
                                <div className="login-content">
                                    <LoginForm />
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        );
    }
}

export default Login;