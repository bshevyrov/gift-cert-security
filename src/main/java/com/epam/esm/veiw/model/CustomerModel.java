package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
public class CustomerModel extends RepresentationModel<CustomerModel> {
    private long id;
    private String name;

}
