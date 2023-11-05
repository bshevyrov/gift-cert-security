import Login from "./Login";
import GiftList from "./GiftList";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import React, {Component} from "react";

import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {getAllCapabilitiesHandler} from "../actions/GiftAction"

//
class App extends Component {

/*

    state = {
        certificates: []
    };


    async componentDidMount() {
        const response = await fetch("/api/v1/gifts", {
            method: "GET",
            headers: {
                "content-type": "application/json",
            },
        })
        const body = await response.json();
        // console.log(body._embedded.giftCertificateModelList);
        this.setState({certificates: body._embedded.giftCertificateModelList})
    }
*/


    render() {
        /*   const {certificates} = this.state;
           return (
               <div className="App">
                   <header className="App-header">
                       <div className="App-intro">
                   <h2>DATA</h2>
                   {certificates.map(certificate=>
                   <div key={certificate.id}>
                       {certificate.name}({certificate.price})
                   </div>
                   )}
                       </div>
                   </header>
               </div>
           );
       }
   }*/

        return (

            <BrowserRouter>
        <Routes>
        <Route path="login"  element={<Login />}/>
            <Route path="certificates"  element={<GiftList />}/>

        </Routes>
        </BrowserRouter>
        )
    }
}

    //
    //   return (
    //
    //     <div className="App">
            // <header className="App-header">
            // <img src={logo} className="App-logo" alt="logo"/>
            // <p>
            // Edit <code>src/App.js</code> and save to reload.
            // </p>
            // <a
            //           className="App-link"
            //           href="https://reactjs.org"
            //           target="_blank"
            //           rel="noopener noreferrer"
            //         >
            //           Learn React
            //         </a>
            //       </header>
            //     </div>
            //   );
            // }
            //

            // function App() {
            //     return(
            //         <div className="App">
            //             <h1>Hello React</h1>
            //
            //         </div>
            //     )
            // }
            export default App;
