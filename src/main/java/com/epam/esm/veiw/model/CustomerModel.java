package com.epam.esm.veiw.model;

import org.springframework.hateoas.RepresentationModel;

public class CustomerModel extends RepresentationModel<CustomerModel> {
    private long id;
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
