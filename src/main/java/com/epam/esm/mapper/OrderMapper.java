package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.entity.OrderEntity;
import com.epam.esm.veiw.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, CustomerMapper.class})
public interface OrderMapper {
    OrderDTO toDTO(OrderEntity orderEntity);

    OrderEntity toEntity(OrderDTO orderDTO);

    List<OrderDTO> toDTOList(List<OrderEntity> orderEntities);
}
