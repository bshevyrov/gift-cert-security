package com.epam.esm.veiw.model;

import org.springframework.hateoas.RepresentationModel;


/**
 * The ResponseModel class extends the Hateoas Representation Model and is required if we want to convert the Customer
 * Entity to a pagination format

**/
public class TagModel extends RepresentationModel<TagModel> {
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

