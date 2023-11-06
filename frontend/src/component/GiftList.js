import React,{Component} from "react";
import {Link} from "react-router-dom";
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

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
                <td> {certificate.createdDate}</td>
                <td style={{whitespace: "nowrap"}}> {certificate.name}</td>
                <td> {certificate.tags}</td>
                <td> {certificate.description}</td>
                <td> {certificate.price}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link}
                                to={"/api/v1/gifts/" + certificate.id}>View???</Button>
                        <Button size="sm" color="primary" tag={Link}
                                to={"/api/v1/gifts/" + certificate.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(certificate.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                {/*<AppNavBar/>*/}
                {/*<Container fluid>*/}
                    {/*<div className="float-right">*/}
                    {/*    <Button color="success" tag={Link} to="/api/v1/gifts/">Add Certificate</Button>*/}
                    {/*</div>*/}
                    <h3>Gift Certificates</h3>
                    <Table className="mt-4">
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
                {/*</Container>*/}
            </div>
        );
    }

}

export default GiftList;