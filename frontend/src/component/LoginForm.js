import React, {Component} from "react";

import "../static/css/userlogin.css";

class LoginForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: {},
            errors: {},
        };
    }

    handleValidation() {
        let fields = this.state.fields;
        let errors = {};
        let formIsValid = true;

        if (!fields["username"]) {
            formIsValid = false;
            errors["username"] = "Cannot be empty."
        }
        if (new String(fields["username"]).length < 3 || new String(fields["username"]).length > 30) {
            formIsValid = false;
            errors["username"] = "Length must be between 3 and 30."
        }
        if (!new String(fields["username"]).match("^[\\w]+$")) {
            formIsValid = false;
            errors["username"] = "Only chars and numbers allowed."
        }

        if (!fields["password"]) {
            formIsValid = false;
            errors["password"] = "Cannot be empty."
        }
        if (new String(fields["password"]).length < 4 || new String(fields["password"]).length > 30) {
            formIsValid = false;
            errors["password"] = "Length must be between 4 and 30."
        }
        if (!new String(fields["password"]).match("^[\\w]+$")) {
            formIsValid = false;
            errors["password"] = "Only chars and numbers allowed."
        }
        this.setState({errors: errors});
        return formIsValid;
    }

    loginSubmit(e) {
        e.preventDefault();

        if (this.handleValidation()) {
            this.sendRequest()
        }
    }

    async sendRequest() {

        let fields = this.state.fields;
        let errors = {};

        const response = await fetch("/api/v1/auth/login", {
            method: "POST",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify({
                "password": fields["password"].valueOf(),
                "username": fields["username"].valueOf(),
            })
        })

        const responseCode = response.status;
        const body = await response.json();

        if (responseCode < 300) {
            localStorage.setItem("token", body.token);
            localStorage.setItem("username", body.username);
            window.location.replace("/")
        } else {
            errors["server"] = body.message;
            this.setState({errors: errors});
        }
    }

    handleChange(field, e) {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

    render() {
        const {username, password} = this.state;
        return (
            <form name="loginForm"
                  className="login-form"
                  onSubmit={this.loginSubmit.bind(this)}
            >
                <div className="login-content">
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        // id="login"
                        value={username}
                        onChange={this.handleChange.bind(this, "username")}
                    />
                    <span className="error-span">{this.state.errors["username"]}</span>
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        // id="password"
                        value={password}
                        onChange={this.handleChange.bind(this, "password")}
                    />
                    <span className="error-span">{this.state.errors["password"]}</span>
                    {/*{error?<Label>{error}</Label>:null}*/}
                    <button type="submit" value="Submit">Login</button>
                    <span className="error-span" id="server-error">{this.state.errors["server"]}</span>
                </div>
            </form>
        );
    }
}

export default LoginForm