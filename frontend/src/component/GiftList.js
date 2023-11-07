import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button, Table} from 'reactstrap';
import NavBar from "./NavBar";
import GiftEdit from "./GiftEdit";
import "../static/css/giftlist.css";


class GiftList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            certificates: [],
            errors: {},

        };

        this.remove = this.remove.bind(this);
        // this.showModal = this.showModal.bind(this);
    }

    //
    // showModal () {
    //     document.getElementById("modal-div-container").style.display = "flex";
    // }

    async componentDidMount() {

        const response = await fetch("/api/v1/gifts?sort=createdDate,desc", {
            method: "GET",
            headers: {
                "content-type": "application/json",
            },
        })
        const body = await response.json();
        // console.log(body._embedded.giftCertificateModelList);
        this.setState({certificates: body._embedded.giftCertificateModelList})
    }

    async remove(id) {
        let errors = {};


        const response = await fetch("/api/v1/gifts/" + id, {
            method: "DELETE",

            headers: {
                "content-type": "application/json",
                "accept": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token").valueOf(),

            },
        })
        const responseCode = response.status;
        // const body = await response.json();

        if (responseCode < 300) {
            window.location.reload();
        } else {
            const body = await response.json();

            errors["server"] = body.message;
            this.setState({errors: errors});
            console.log(responseCode);
        }
        //todo if code ok  then


    }

    render() {
        const {certificates} = this.state;


        const certificateList = certificates.map(certificate => {

            return <tr key={certificate.id}>
                <td> {new String(certificate.createdDate).replace("T", " ")}</td>
                <td> {certificate.name}</td>
                <td className="tag-container">
                    <div> {certificate.tagModels.map((number) =>
                        <p key={number.id}>{number.name}</p>)

                    }
                    </div>
                </td>
                <td> {certificate.description}</td>
                <td> {Math.ceil(certificate.price * 100) / 100}</td>
                <td>
                    {/*<ButtonGroup>*/}
                    <Button size="sm" color="primary" tag={Link}
                            to={"/api/v1/gifts/" + certificate.id}>View???</Button>
                    <Button size="sm" color="primary" tag={Link}
                            to={"/api/v1/gifts/" + certificate.id}>Edit</Button>


                    <button onClick={() => this.remove(certificate.id)}>Delete</button>

                    {/*</ButtonGroup>*/}
                </td>
            </tr>
        });

        return (

            <div className="innerHTML-container">

                <NavBar/>
                <main>
                    <div className="main-container">
                        <div className="table-container">
                            <Table>
                                <thead>
                                <tr>
                                    <th>Create date</th>
                                    <th>Name</th>
                                    <th>Tag</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                {certificateList}
                                </tbody>
                            </Table>
                        </div>
                        {/*
                </Container>
*/}
                        <button
                            onClick={() => document.getElementById("modal-div-container").style.display = "flex"}>Add
                            New
                        </button>

                    </div>
                    <GiftEdit/>
                </main>
            </div>
        );
    }

}

export default GiftList;