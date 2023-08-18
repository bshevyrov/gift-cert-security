package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.OrderMapper;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.service.OrderService;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class OrderFacadeImpl implements OrderFacade {
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Autowired
    public OrderFacadeImpl(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return orderMapper.toDTO(
                orderService.create(
                        orderMapper.toEntity(orderDTO)));
    }

    @Override
    @Deprecated
    public OrderDTO findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public Page<OrderDTO> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public OrderDTO update(OrderDTO entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<OrderDTO> findAllByCustomerId(long id, Pageable pageable) {
        Page<OrderEntity> orders = orderService.findAllByCustomerId(id, pageable);
        return new PageImpl<>(orderMapper.toDTOList(orders.getContent()), pageable, orders.getTotalElements());
    }

    @Override
    public OrderDTO getPopularTagInOrderByCustomerId(Long id) {
        OrderEntity popularTagInOrderByCustomerId = orderService.getPopularTagInOrderByCustomerId(id);
        return orderMapper.toDTO(popularTagInOrderByCustomerId);
    }
}
