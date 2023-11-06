import React, { Component } from "react";
import NavBar from "./NavBar";
let __html = require("../static/page/loginPage");
let template = { __html: __html };

class Login extends Component {

    render() {
        return (

            <div className="innerHTML-container">
            <NavBar />

                <span dangerouslySetInnerHTML={template}/>
            </div>
        );
    }
}

export default Login;