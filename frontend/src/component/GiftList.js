import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button, Table} from 'reactstrap';
import NavBar from "./NavBar";
// import PaginatedItems from "./PaginatedItems";
import PaginationButton from "./PaginationButton";
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
        this.goToPage = this.goToPage.bind(this);
        this.preparedUrl = this.preparedUrl.bind(this);
        this.changeCertificates = this.changeCertificates.bind(this);
    }




    //
    // showModal () {
    //     document.getElementById("modal-div-container").style.display = "flex";
    // }
    goToPage(pageNum) {
        console.log(this.state.pageInfo);
        localStorage.setItem("page", pageNum);
        window.location.reload();
    }

    preparedUrl() {
        let url = "/api/v1/gifts?"
            + "page=" + (localStorage.getItem("currentPage") || "0") + "&"
            + "sort=" + (localStorage.getItem("sort") || "createdDate,desc") + "&"
            + "size=" + (localStorage.getItem("size") || "10");

        return url;
    }

     async componentDidMount() {
        let  rsl =  await this.loadData();

         this.setState({certificates: rsl._embedded.giftCertificateModelList})
        this.setState({pageInfo: rsl.page})
    }
   async  changeCertificates(){
        // localStorage.setItem("currentPage",localStorage.getItem("currentPage"));
        let response =  await this.loadData();
        this.setState({certificates: response._embedded.giftCertificateModelList})
        console.log("2");

    }


    async loadData() {
        const response = await fetch(this.preparedUrl(), {
            method: "GET",
            headers: {
                "content-type": "application/json",
            },
        })
        // console.log(body._embedded.giftCertificateModelList);
        return   await response.json();
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
                        <div>
                            <select name="cars" id="cars"
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
                        <div className="pages-container">
                            <PaginationButton
                                pageInfo={this.state.pageInfo}
                                reloadFunction = {this.changeCertificates}
                            />

                        </div>
                    </div>

                    <GiftEdit/>
                </main>
            </div>
        );
    }

}

export default GiftList;