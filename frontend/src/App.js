import './App.css';
import Login from "./Login";
import React, {Component} from "react";
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import login from "./login.html"

class App extends Component {

    state = {
        certificates: []
    };

    async componentDidMount() {
      const response = await fetch("/api/v1/gifts");
      const body = await response.json();
      this.setState({certificates:body})
    }

    render() {
    const certificates = this.state

        <div>

        </div>
        // return (
        //     <BrowserRouter>
        //         <Routes>
        //             <Route path="login" exact={true} component={Login}/>
        //
        //         </Routes>
        //     </BrowserRouter>
        // )
    }
}

//
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
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
export default App;
