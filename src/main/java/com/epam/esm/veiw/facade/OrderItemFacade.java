package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemFacade extends BaseFacade<OrderItemDTO> {

    List<OrderItemDTO> createAll(List<OrderItemDTO> orderItemDTOS);
}
