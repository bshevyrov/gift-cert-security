import React, {Component} from "react";
import {Link} from "react-router-dom";
import "../static/css/navbar.css";


export default class NavBar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false}
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <nav>
                <div className="logo-menu-container">
                    <div className="menu-container">
                        <span className="material-icons md-dark"> menu </span>
                        <div className="dropdown-content">
                            <Link to="/">Home</Link>
                            <Link to="/certificates">Certificates</Link>
                            <Link to="/login">Login</Link>
                        </div>
                    </div>
                    <div className="mini-logo-container">
                        <img src="./img/Safeimagekit-resized-img.png" alt="Mini Logo" className="logo-mini"/>
                    </div>
                </div>
                <div className="search-container">
                    <div className="search-bar">
                        <form><input type="search" name="query" placeholder="Search by item name"/></form>
                    </div>
                    <div className="search-dropdown"><select>
                        <option value="all" selected="selected">All categories</option>
                        <option value="tag">Tag</option>
                        <option value="name">Name</option>
                        <option value="description">Description</option>
                    </select></div>
                </div>
                <div className="sign-in-container"><span className="material-icons-outlined"> favorite </span> <span
                    className="material-icons-outlined"> shopping_cart </span>
                    <span>
                        <Link to="/login">Login</Link> |  <Link to="/login">Sing Up</Link> </span></div>
            </nav>);

    }
    }