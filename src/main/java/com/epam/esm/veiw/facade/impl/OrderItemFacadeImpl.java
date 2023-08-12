package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.OrderItemMapper;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.service.OrderItemService;
import com.epam.esm.veiw.dto.OrderItemDTO;
import com.epam.esm.veiw.facade.OrderItemFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class OrderItemFacadeImpl implements OrderItemFacade {
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    public OrderItemFacadeImpl(OrderItemService orderItemService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItemDTO create(OrderItemDTO entity) {
        return null;
    }

    @Override
    public OrderItemDTO findById(long id) {
        return null;
    }

    @Override
    public Page<OrderItemDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(OrderItemDTO entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<OrderItemDTO> createAll(List<OrderItemDTO> orderItemDTOS) {
        return orderItemMapper.toDTOList(orderItemService.createAll(orderItemMapper.toEntityList(orderItemDTOS)));
    }
}
