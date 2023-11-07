import React, {Component} from "react";

import "../static/css/editgift.css";
import {Button} from "reactstrap";
import CreatableSelect from 'react-select/creatable';

class GiftEdit extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tags: [],
            multiValue: [],
            fields: {},
            errors: {},
        };

        this.handleMultiChange = this.handleMultiChange.bind(this);
        this.handleCreate = this.handleCreate.bind(this);

    }

    handleValidation() {
        let fields = this.state.fields;
        let multiValue = this.state.multiValue;
        let errors = {};
        let formIsValid = true;

        if (!fields["name"]) {
            formIsValid = false;
            errors["name"] = "Cannot be empty."
        }
        if (!fields["description"]) {
            formIsValid = false;
            errors["description"] = "Cannot be empty."
        }
        if (!fields["price"]) {
            formIsValid = false;
            errors["price"] = "Cannot be empty."
        }
        if (!fields["duration"]) {
            formIsValid = false;
            errors["duration"] = "Cannot be empty."
        }
        if (multiValue.length < 1) {
            formIsValid = false;
            errors["tags"] = "Cannot be empty."
        }

        if (new String(fields["name"]).length < 6 || new String(fields["name"]).length > 30) {
            formIsValid = false;
            errors["name"] = "Length must be between 6 and 30."
        }
        if (new String(fields["description"]).length < 12 || new String(fields["description"]).length > 1000) {
            formIsValid = false;
            errors["description"] = "Length must be between 12 and 1000."
        }

        if (fields["price"] <= 0) {
            formIsValid = false;
            errors["price"] = "Cannot be zero or negative."
        }
        if (fields["duration"] < 0) {
            formIsValid = false;
            errors["duration"] = "Cannot be negative."
        }
        if (fields["duration"] % 1 !== 0) {
            formIsValid = false;
            errors["duration"] = "Cannot be float."
        }

        for (let multiValueElement of multiValue) {
            if (multiValueElement.length < 3 || multiValueElement.length > 15) {
                formIsValid = false;
                errors["tags"] = "Length must be between 3 and 15"
            }
        }


        this.setState({errors: errors});
        return formIsValid;
    }

    loginSubmit(e) {
        e.preventDefault();
        if (this.handleValidation()) {
            this.sendRequest()
        }
    }

    async componentDidMount() {

        const response = await fetch("/api/v1/tags", {
            method: "GET",
            headers: {
                "content-type": "application/json",
            },
        })
        const body = await response.json();
        // console.log(body._embedded.giftCertificateModelList);

        this.setState({
            tags: body._embedded.tagModelList.map(
                tag => ({value: tag.name, label: tag.name}))
        });
    }

    handleMultiChange(option) {
        this.setState(state => {
            return {
                multiValue: option
            };
        });
    }

    handleChange(field, e) {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

    handleCreate(inputValue) {
        const newOption = {value: inputValue, label: inputValue};

        this.setState({multiValue: [...this.state.multiValue, newOption]});

    };


    render() {
        const {tags} = this.state


        return (

            <div className="modal-div-container">
                <div className="modal-div-content">

                    <div className="category-panel ">
                        <p>Add New Gift Certificate</p>
                    </div>
                    <div className="outer-container">
                        <div className="column-container-left">
                            <div className="row">
                                <p>Title</p>
                            </div>
                            <div className="row">
                                <p>Description</p>
                            </div>
                            <div className="row">
                                <p>Duration</p>
                            </div>
                            <div className="row">
                                <p>Price</p>
                            </div>
                            <div className="row">
                                <p>Tags</p>
                            </div>
                        </div>
                        <div className="column-container-right">
                            <form

                                // onSubmit={this.loginSubmit.bind(this)}
                            >
                                <div className="row">
                                    <input type="text" id="name"
                                           name="name" onChange={this.handleChange.bind(this, "name")}/>
                                    <span className="error-span">{this.state.errors["name"]}</span>

                                </div>
                                <div className="row">
                                    <textarea id="description" rows="6"
                                              name="description"
                                              onChange={this.handleChange.bind(this, "description")}/>
                                    <span className="error-span">{this.state.errors["description"]}</span>

                                </div>
                                <div className="row">
                                    <input type="number" id="duration"
                                           name="duration" onChange={this.handleChange.bind(this, "duration")}/>
                                    <span className="error-span">{this.state.errors["duration"]}</span>

                                </div>
                                <div className="row">
                                    <input type="number" id="price"
                                           name="price" onChange={this.handleChange.bind(this, "price")}
                                    />
                                    <span className="error-span">{this.state.errors["price"]}</span>

                                </div>
                                <div className="row">
                                    <CreatableSelect
                                        isMulti
                                        isClearable
                                        onChange={this.handleMultiChange}
                                        onCreateOption={this.handleCreate}
                                        options={tags}
                                        value={this.state.multiValue}
                                    />
                                    <span className="error-span">{this.state.errors["tags"]}</span>

                                </div>
                                {/*//TODO console.log(value);*/}
                            </form>
                        </div>


                        <div className="button-container">
                            {/*<Button>Save</Button>*/}
                            <button type="submit" value="Submit" onClick={this.loginSubmit.bind(this)}>Login</button>

                            <Button>Cancel</Button>
                        </div>
                    </div>


                </div>

            </div>
        );
    }
}

export default GiftEdit;