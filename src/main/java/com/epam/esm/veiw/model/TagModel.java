package com.epam.esm.veiw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


/**
 * The TagModel class extends the Hateoas Representation Model and is required if we want to convert the TagDTO
 * to a pagination format
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TagModel extends RepresentationModel<TagModel> {
    private long id;
    private String name;
}

