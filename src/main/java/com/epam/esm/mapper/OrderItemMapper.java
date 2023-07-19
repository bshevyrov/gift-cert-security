package com.epam.esm.mapper;

import com.epam.esm.entity.OrderItem;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GiftCertificateMapper.class})
public interface OrderItemMapper {
    @Mapping(source = "giftCertificate",target = "giftCertificateDTO")
    OrderItemDTO toDTO(OrderItem orderItem);

    OrderItem toModel(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> toDTOList(List<OrderItem> orderItems);
}
