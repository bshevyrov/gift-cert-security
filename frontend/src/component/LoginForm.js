import React, {Component} from "react";


class LoginForm extends Component {
    initialState = {
        login: "",
        password: ""
    }
    state = this.initialState;

    handleChange = (event) => {
        const {name ,value} = event.target

        this.setState({
            [name]: value,
        })
    }
render() {
        const {login, password }= this.state;
        return(


        )
}

}
export default LoginForm