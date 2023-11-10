import {Button} from "reactstrap";
import React, {Component} from "react";
import "../static/css/deleteconfirm.css";
import {sendRemoveRequest} from "./Utils";


export default class DeleteConfirm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id:{},
        };
    }
setId(id){
       this.setState({id: id});
}

async remove(){

    const response = await sendRemoveRequest(this.state.id);

}

    render() {
const {id} = this.state;
console.log(id);
        return (
            <div className="delete-container" id ="delete-container">
            <div className="delete-alert">
                <div className="panel-header">
                    <p>Delete confirmation</p>
                </div>

                <div className="panel-body">
                    <p>Do you really want to delete certificate with id = {""+id} ?</p>
                    </div>
                <div className="button-container">
                    <Button color="danger" onClick={()=>this.remove()}>DELETE</Button>

                    <Button color="secondary" onClick={()=>  document.getElementById("delete-container").style.display="none"}>Cancel</Button>
                </div>
                </div>
            </div>

        );


    }
}