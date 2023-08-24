package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * The GiftCertificateModel class extends the Hateoas Representation Model and is required if we want to convert the GiftCertificateDTO
 * to a pagination format
 **/
@Getter
@Setter
public class GiftCertificateModel extends RepresentationModel<GiftCertificateModel> {
    private long id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private List<TagModel> tagModels;
}

