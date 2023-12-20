import React, {Component} from "react";

import "../static/css/editgift.css";
import CreatableSelect from 'react-select/creatable';
import {sendRequestCreate, sendRequestUpdate, sendTagRequest} from "./Utils";


export default class GiftEdit extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id:0,
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

      crateSubmit(e) {
        e.preventDefault();
        if (this.handleValidation()) {
            this.sendRequest()
        }
    }

    async sendRequest() {
        let fields = this.state;
        let response;
        if (fields.id===0) {
            response = await sendRequestCreate(this.state.multiValue, fields.fields);

        } else {
            response = await sendRequestUpdate(this.state.multiValue, fields.fields);

        }
        const responseCode = response.status;
        if (responseCode < 300) {
            window.location.reload();
        } else {
            const body = await response.json();
            this.props.setErrorMessage("" + await body.message);
        }
        document.getElementById("modal-div-container").style.display = "none";
    }

    prepareModal(gift) {
        let fields = this.state.fields;
         this.getAllTags();

        document.getElementById("name").value = fields["name"] = gift ? gift.name : "";
        document.getElementById("description").value = fields["description"] = gift ? gift.description : "";
        document.getElementById("price").value = fields["price"] = gift ? gift.price : "";
        document.getElementById("duration").value = fields["duration"] = gift ? gift.duration : "";
       this.setState({id:  gift ? gift.id : 0});
        this.setState({fields});
        this.setState({
            multiValue: gift ?
                gift.tagModels.map(
                    (tag) => ({value: tag.name, label: tag.name}))
                : []
        })
        if (!gift) {
            document.getElementById("modal-div-container").style.display = "none";
        }
    }

    async componentDidMount() {
        await this.getAllTags();
        this.prepareModal()
    }

    async getAllTags() {
        const body = await sendTagRequest();
        let fields = this.state.fields;
        fields["tags"] = body._embedded.tagModelList.map(
            tag => ({value: tag.name, label: tag.name}));
        this.setState({fields});
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
        const {tags} = this.state.fields;
        return (

            <div className="modal-div-container" id="modal-div-container">
                <div className="modal-div-content">

                    <div className="category-panel" id="category-panel">
                        <p id="category-panel-title">Add New Gift Certificate</p>
                    </div>
                    <div className="outer-container">
                        <div className="internal-container">
                            <div className="row">
                                <div className="column-label">
                                    <p>Title</p>
                                </div>
                                <div className="column">
                                    <input type="text" id="name"
                                           name="name" onChange={this.handleChange.bind(this, "name")}/>
                                    <span className="error-span">{this.state.errors["name"]}</span>
                                </div>
                            </div>

                            <div className="row">
                                <div className="column-label">
                                    <p>Description</p>
                                </div>
                                <div className="column">
                                    <textarea id="description" rows="6"
                                              name="description"
                                              onChange={this.handleChange.bind(this, "description")}/>
                                    <span className="error-span">{this.state.errors["description"]}</span>
                                </div>
                            </div>
                            <div className="row">
                                <div className="column-label">
                                    <p>Duration</p>
                                </div>
                                <div className="column">
                                    <input type="number" id="duration"
                                           name="duration" onChange={this.handleChange.bind(this, "duration")}/>
                                    <span className="error-span">{this.state.errors["duration"]}</span>
                                </div>
                            </div>
                            <div className="row">
                                <div className="column-label">
                                    <p>Price</p>
                                </div>
                                <div className="column">
                                    <input type="number" id="price"
                                           name="price" onChange={this.handleChange.bind(this, "price")}
                                    />
                                    <span className="error-span">{this.state.errors["price"]}</span>
                                </div>
                            </div>
                            <div className="row">
                                <div className="column-label">
                                    <p>Tags</p>
                                </div>
                                <div className="column">
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
                            </div>
                            <div className="button-container">
                                <button className="submit-btn" type="submit" value="Submit"
                                        onClick={this.crateSubmit.bind(this)}>Submit
                                </button>

                                <button className="back-btn"
                                        onClick={() => this.prepareModal()}>
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

