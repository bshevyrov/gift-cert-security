package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper. Automatically implemented by mapstruct.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface OrderMapper {

    @Mapping(source = "customerEntity", target = "customerDTO")
    OrderDTO toDTO(OrderEntity orderEntity);
    @Mapping(target = "createdDate",ignore = true )
    @Mapping(target = "updatedDate",ignore = true )
    @Mapping(target = "deletedDate",ignore = true )
    @Mapping(target = "customerEntity", source = "customerDTO")
    OrderEntity toEntity(OrderDTO orderDTO);



    List<OrderDTO> toDTOList(List<OrderEntity> orderEntities);

}
