import React, {Component} from "react";
import "../static/css/userlogin.css";

class LoginForm extends Component {

    // initialState = {
    //     login: "",
    //     password: ""
    // }
    // state = this.initialState;
    //
    // handleChange = (event) => {
    //     const {name, value} = event.target
    //
    //     this.setState({
    //         [name]: value,
    //     })
    // }

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

        if (!fields["login"]) {
            formIsValid = false;
            errors["login"] = "Cannot be empty."
        }
        if (new String(fields["login"]).length < 3 || new String(fields["login"]).length > 30) {
            formIsValid = false;
            errors["login"] = "Length must between 3 and 30."
        }

        if (!fields["password"]) {
            formIsValid = false;
            errors["password"] = "Cannot be empty."
        }
        if (new String(fields["password"]).length < 4 || new String(fields["password"]).length > 30) {
            formIsValid = false;
            errors["password"] = "Length must between 4 and 30."
        }

        this.setState({errors: errors});
        return formIsValid;
    }

    loginSubmit(e) {
        e.preventDefault();

        if (this.handleValidation()) {
            alert("Form submitted");
        } else {
            // alert("Form has errors.");
        }

    }

    handleChange(field, e) {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

    render() {
        const {login, password} = this.state;
        return (
            <form name="loginForm"
                  className="login-form"
                  onSubmit={this.loginSubmit.bind(this)}
            >
                {/*  <label htmlFor="login">Login*/}
                {/*</label>*/}
                <div className="login-content">

                    <input
                        type="text"
                        name="login"
                        placeholder="Login"
                        // id="login"
                        // value={login}
                        onChange={this.handleChange.bind(this, "login")}
                        value={this.state.fields["login"]}
                    />
                    <span className="error-span">{this.state.errors["login"]}</span>
                    {/*<label htmlFor="password">Password</label>*/}
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"

                        // id="password"
                        // value={password}
                        onChange={this.handleChange.bind(this, "password")}
                        value={this.state.fields["password"]}
                    />
                    <span className="error-span">{this.state.errors["password"]}</span>
                    <button type="submit" value="Submit">Login</button>
                </div>

            </form>
        );
    }

}

export default LoginForm