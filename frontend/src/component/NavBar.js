import React, {Component} from "react";
import {Link} from "react-router-dom";
import "../static/css/navbar.css";
import {Button} from "reactstrap";
import {sendSearchTagRequest,sendSearchNameOrDesrRequest} from "./Utils";


export default class NavBar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false}
        // this.toggle = this.toggle.bind(this);
        this.prepareSearch = this.prepareSearch.bind(this);
    }

    // toggle() {
    //     this.setState({
    //         isOpen: !this.state.isOpen
    //     });
    // }

    prepareSearch = async ()=> {
        //TODO SEARCH PAGINATION
        let search = document.getElementById("search").value;
        let words = new String(search).split(" ");
        let tags = [];
        let name;
        words.forEach((word) => {
           if (word.includes("#")){
               tags.push(word.trim()
                   .replace("#(","")
                   .replace(")",""));
           }
           else {
               name=word.trim();
           }
        })
        let  filteredRequest;
        if(tags.length>0){
          const  response = await sendSearchTagRequest(tags);

          if(name){
              filteredRequest = response._embedded.giftCertificateModelList.filter((giftCert)=>{
                  giftCert.name.contains(name) || giftCert.description.contains(name)
          })
          } else {
            filteredRequest = response._embedded.giftCertificateModelList;
          }

        } else {
            const  response = await sendSearchNameOrDesrRequest(name);
            filteredRequest = response._embedded.giftCertificateModelList;

        }
        this.props.change(filteredRequest);
        document.getElementById("nav-panel").style.display="none";


    }

    render() {
        return (
            <nav>
                <div className="logo-menu-container">
                    <div className="menu-container">
                        <span className="material-icons md-dark"> menu </span>
                        <div className="dropdown-content">
                            <Link to="/">Home</Link>
                            <Link to="/certificates" >Certificates</Link>
                            <Link to="/login">Login</Link>
                        </div>
                    </div>
                    <div className="mini-logo-container">
                        <img src="./img/Safeimagekit-resized-img.png" alt="Mini Logo" className="logo-mini"/>
                    </div>
                </div>
                <div className="search-container">
                    <div className="search-bar">
                        <input id = "search" type="search" name="query" placeholder="Search by item name"/>
                    </div>
                    <div className="search-dropdown">
                      <Button color="primary" onClick={this.prepareSearch}>Search</Button>
                    </div>
                </div>
                <div className="sign-in-container"><span className="material-icons-outlined"> favorite </span> <span
                    className="material-icons-outlined"> shopping_cart </span>
                    <span>
                        {!localStorage.getItem("username") ?
                            (<div><Link to="/login">Login</Link> | <Link to="/login">Sing Up</Link></div>)
                            :
                            (<div><p>{localStorage.getItem("username")}</p> |<Button onClick={() => {
                                localStorage.clear();
                                window.location.replace("/login");
                            }
                            } name="Log out"> log out</Button></div>)
                        }
                    </span></div>
            </nav>);

    }
}