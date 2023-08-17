package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GiftCertificateMapper.class})
public interface OrderItemMapper {

    @Mapping(target ="orderDTO",source = "orderEntity")
    @Mapping(target ="giftCertificateDTO",source = "giftCertificateEntity")
    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);
    @Mapping(target = "createdDate",ignore = true )
    @Mapping(target = "updatedDate",ignore = true )
    @Mapping(source ="orderDTO.id",target = "orderEntity.id")
    @Mapping(source ="giftCertificateDTO",target = "giftCertificateEntity")
    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> toDTOList(List<OrderItemEntity> orderItemEntities);

    List<OrderItemEntity> toEntityList(List<OrderItemDTO> orderItemDTOS);
}
