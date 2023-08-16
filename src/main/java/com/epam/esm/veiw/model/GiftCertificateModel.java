package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The ResponseModel class extends the Hateoas Representation Model and is required if we want to convert the Customer
 * com.epam.esm.persistence.entity.Entity to a pagination format

**/
@Getter
@Setter
public class GiftCertificateModel extends RepresentationModel<GiftCertificateModel> {
    private long id;
    private String name;
    private String description;
    private double price;
    private int duration;
//    private LocalDateTime createDate;
//    private LocalDateTime lastUpdateDate;
    private List<TagModel> tagModels;


}

