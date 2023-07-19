package com.epam.esm.veiw.model;

import com.epam.esm.veiw.dto.OrderItemDTO;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

public class OrderModel extends RepresentationModel<OrderModel> {
    private long id;
    private LocalDateTime createTime;
    private List<OrderItemModel> orderItemModelList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<OrderItemModel> getOrderItemModelList() {
        return orderItemModelList;
    }

    public void setOrderItemModelList(List<OrderItemModel> orderItemModelList) {
        this.orderItemModelList = orderItemModelList;
    }


}
