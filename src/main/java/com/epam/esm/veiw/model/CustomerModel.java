package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

/**
 * The CustomerModel class extends the Hateoas Representation Model and is required if we want to convert the CustomerDTO
 * to a pagination format
 **/
@Getter
@Setter
public class CustomerModel extends RepresentationModel<CustomerModel> {
    private long id;
    private String username;
}
