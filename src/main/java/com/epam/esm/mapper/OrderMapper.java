package com.epam.esm.mapper;

import com.epam.esm.entity.Order;
import com.epam.esm.veiw.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, CustomerMapper.class})
public interface OrderMapper {
    @Mapping(source = "customer",target = "customerDTO")
    @Mapping(source = "orderItemList",target = "orderItemDTOList")
    OrderDTO toDTO(Order order);

    Order toModel(OrderDTO orderDTO);

    List<OrderDTO> toDTOList(List<Order> orders);
}
