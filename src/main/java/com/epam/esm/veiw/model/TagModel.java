package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


/**
 * The ResponseModel class extends the Hateoas Representation Model and is required if we want to convert the Customer
 * com.epam.esm.persistence.entity.Entity to a pagination format

**/
@Getter
@Setter
public class TagModel extends RepresentationModel<TagModel> {
    private long id;
    private String name;

}

