package com.epam.esm.veiw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO extends AbstractDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createTime;
    private List<OrderItemDTO> orderItemDTOList;
    private CustomerDTO customerDTO;

    public OrderDTO() {
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
