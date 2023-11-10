import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button, ButtonGroup, Table} from 'reactstrap';
import NavBar from "./NavBar";
import PaginationButton from "./PaginationButton";
import {sendRequest} from "./Utils";
import GiftEdit from "./GiftEdit";
import "../static/css/giftlist.css";


class GiftList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            certificates: [],
            pageInfo: {},
            errors: {},
            body: {},
        };

        this.remove = this.remove.bind(this);
        this.setErrorMessage = this.setErrorMessage.bind(this);
        this.changeCertificates = this.changeCertificates.bind(this);
        this.setSearchCertificates = this.setSearchCertificates.bind(this);
    }


    async componentDidMount() {
        let rsl = await sendRequest();

        this.setState({certificates: rsl._embedded.giftCertificateModelList})
        this.setState({pageInfo: rsl.page})
    }

    async changeCertificates() {
        let response = await sendRequest();
        this.setState({certificates: response._embedded.giftCertificateModelList})

    }

    setSearchCertificates = (cert) => {
        this.setState({certificates: cert});

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

        if (responseCode < 300) {
            window.location.reload();
        } else {
            const body = await response.json();
            //todo if code ok  then
            errors["server"] = body.message;
            this.setState({errors: errors});
            console.log(responseCode);
        }

    }

    changeSort = async (e) => {
        let id = e.target.id;
        let field = id === "date-sort-btn" ? "createdDate" : "name";
        let textContext = document.getElementById(id).textContent;
        if (textContext.indexOf("down") === -1) {
            if (textContext.indexOf("double") === -1) {
                document.getElementById(id).textContent = "keyboard_double_arrow_up";
                localStorage.setItem("sort", field + ",asc");

            } else {
                document.getElementById(id).textContent = "keyboard_double_arrow_down";
                localStorage.setItem("sort", field + ",desc");
            }
        } else {
            if (textContext.indexOf("double") === -1) {
                document.getElementById(id).textContent = "keyboard_double_arrow_down";
                localStorage.setItem("sort", field + ",desc");
            } else {
                document.getElementById(id).textContent = "keyboard_double_arrow_up";
                localStorage.setItem("sort", field + ",asc");
            }
        }
        let rsl = await sendRequest();


        this.setState({certificates: rsl._embedded.giftCertificateModelList});
        this.setState({pageInfo: rsl.page});
        //
        //
        //
        // document.getElementById("date-sort-btn").textContent === "keyboard_double_arrow_up" ?
        //     document.getElementById("date-sort-btn").textContent = "keyboard_double_arrow_down"
        //
        //
        //
        //     console.log(e.target.id);
        // console.log("id");
        // document.getElementById("date-sort-btn");
        // document.getElementById("name-sort-btn");
    }
    setErrorMessage = async (err) => {
        let errors = {};
        errors["server"] = await err;
        this.setState({errors: errors});
        document.getElementById("error-field").style.display = "flex";
    }

    render() {
        const {certificates} = this.state;

        const certificateList = certificates.map(certificate => {

            return <tr key={certificate.id}>
                <td className="date-col"> {new String(certificate.createdDate).replace("T", " ")}</td>
                <td className="name-col"> {certificate.name}</td>
                <td className="tag-col">
                    <div id="tag-div">
                        {certificate.tagModels.map((number) =>
                            <p key={number.id}>{number.name}</p>)
                        }
                    </div>
                </td>
                <td className="descr-col"> {certificate.description}</td>
                <td className="price-col"> {Math.ceil(certificate.price * 100) / 100}</td>
                <td className="action-col">
                    <ButtonGroup size="sm">
                        <Button color="primary" tag={Link}
                                to={"/api/v1/gifts/" + certificate.id}>View???</Button>
                        <Button color="primary" tag={Link}
                                to={"/api/v1/gifts/" + certificate.id}>Edit</Button>
                        <Button color="danger" onClick={() => this.remove(certificate.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (

            <div className="innerHTML-container">

                <NavBar change={this.setSearchCertificates}/>
                <main>
                    <div className="error-field" id="error-field">
                        <span className="error-span">{this.state.errors["server"]}</span>
                        <Button id="close-btn" color="danger" onClick={
                            () => document.getElementById("error-field").style.display = "none"
                        }>X</Button>
                    </div>
                    <div className="main-container">
                        <div className="table-container">
                            <Table>
                                <thead>
                                <tr>

                                    <th className="date-col">Create date
                                        <button id="date-sort-btn" onClick={(e) => this.changeSort(e)}
                                                className="material-symbols-outlined">
                                            keyboard_double_arrow_up
                                        </button>
                                    </th>

                                    <th className="name-col">Name
                                        <span id="name-sort-btn" onClick={(e) => this.changeSort(e)}
                                              className="material-symbols-outlined">
                                        keyboard_double_arrow_down
                                        </span></th>
                                    <th className="tag-col">Tag</th>
                                    <th className="descr-col">Description</th>
                                    <th className="price-col">Price</th>
                                    <th className="action-col">Actions</th>
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
                        <div id="nav-panel">
                            <div>
                                <Button color="success"
                                        onClick={() => document.getElementById("modal-div-container").style.display = "flex"}>Add
                                    New
                                </Button>
                            </div>

                            <div className="pages-container">
                                <PaginationButton
                                    pageInfo={this.state.pageInfo}
                                    reloadFunction={this.changeCertificates}
                                />

                            </div>
                            <div>
                                <select className="form-select"
                                        onChange={(e) => {
                                            localStorage.setItem("size", e.target.value)
                                            localStorage.setItem("currentPage", 0)
                                            window.location.reload()
                                        }}
                                        defaultValue={localStorage.getItem("size") || "10"}>
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <GiftEdit setErrorMessage={this.setErrorMessage}/>
                </main>
            </div>
        );
    }

}

export default GiftList;