package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.entity.OrderItemEntity;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GiftCertificateMapper.class})
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);

    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> toDTOList(List<OrderItemEntity> orderItemEntities);
}
