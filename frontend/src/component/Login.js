import React, { Component } from "react";
let __html = require("../static/page/loginPage");
let template = { __html: __html };

class Login extends Component {

    render() {
        return (
            <div className="screen-share">
                <span dangerouslySetInnerHTML={template} />
            </div>
        );
    }
}
export default Login;