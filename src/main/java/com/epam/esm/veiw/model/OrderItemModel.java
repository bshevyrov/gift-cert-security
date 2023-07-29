package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {
    private long id;
    private int quantity;
    private GiftCertificateModel giftCertificateModel;

}
