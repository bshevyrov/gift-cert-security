package com.epam.esm.veiw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * The OrderModel class extends the Hateoas Representation Model and is required if we want to convert the OrderDTO
 * to a pagination format
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderModel extends RepresentationModel<OrderModel> {
    private long id;
    private LocalDateTime createdDate;
    private double cost;
}
