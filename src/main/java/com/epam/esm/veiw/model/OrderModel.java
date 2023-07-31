package com.epam.esm.veiw.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderModel extends RepresentationModel<OrderModel> {
    private long id;
    private LocalDateTime createTime;
    private double cost;
}