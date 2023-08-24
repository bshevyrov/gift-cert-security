package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.veiw.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper. Automatically implemented by mapstruct.
 */
@Mapper(componentModel = "spring", uses = {/*CustomerMapper.class,*/OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "customerEntity", target = "customerDTO")
    OrderDTO toDTO(OrderEntity orderEntity);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "customerEntity", source = "customerDTO")
    @Mapping(target = "orderItemEntities", source = "orderItemDTOS")
    OrderEntity toEntity(OrderDTO orderDTO);


    List<OrderDTO> toDTOList(List<OrderEntity> orderEntities);

}
