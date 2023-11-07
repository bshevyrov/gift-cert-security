import React,{Component} from "react";
import {Link} from "react-router-dom";
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import NavBar from "./NavBar";
import GiftEdit from "./GiftEdit";
import "../static/css/giftlist.css";



class GiftList extends Component {

    constructor(props) {
        super(props);
        this.state = {certificates: []};
        this.remove = this.remove.bind(this);
    }

    async componentDidMount() {
        //TODO ADD SORT BY DATE
    /*    fetch("/api/v1/gifts", {
            method: "GET",
            headers: {
                "content-type": "application/json",
            }
        }).then(code => console.log(code.statusCode))
            .then(response => response.body._embedded.giftCertificateModelList)
            .then(data => this.setState({certificates: data}));

        */
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

    async remove(id) {
        await fetch("/api/v1/gifts/${id}", {
            method: "DELETE",
            headers: {
                "content-type": "application/json",
                "accept": "application/json",
            },
        }).then(() => {
            let updatedCertificates = [...this.state.certificates].filter(i => i.id !== id);
            this.setState({updatedCertificates});
        });
    }

    render() {
        const {certificates, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }
        const certificateList = certificates.map(certificate => {

            return <tr key={certificate.id}>
                <td> {new String(certificate.createdDate).replace("T"," ")}</td>
                <td > {certificate.name}</td>
                <td className="tag-container"><div> {certificate.tagModels.map((number) =>
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
                        <Button size="sm" color="danger" onClick={() => this.remove(certificate.id)}>Delete</Button>

                    {/*</ButtonGroup>*/}
                </td>
            </tr>
        });

        return (

            <div className="innerHTML-container">

                <NavBar />
                <main>
                    <div className="main-container">
                {/*<AppNavBar/>*/}
                {/*<Container fluid>*/}
                    {/*<div className="float-right">*/}
                    {/*    <Button color="success" tag={Link} to="/api/v1/gifts/">Add Certificate</Button>*/}
                    {/*</div>*/}

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
                    </div>
                </main>
                <GiftEdit />

            </div>

        );
    }

}

export default GiftList;