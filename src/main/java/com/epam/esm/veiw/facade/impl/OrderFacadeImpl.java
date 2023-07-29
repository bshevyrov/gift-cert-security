package com.epam.esm.veiw.facade.impl;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.veiw.facade.OrderFacade;
import com.epam.esm.mapper.OrderMapper;
import com.epam.esm.service.OrderService;
import com.epam.esm.veiw.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public OrderDTO create(OrderDTO entity) {
        return null;
    }

    @Override
    public OrderDTO findById(long id) {
        return null;
    }

    @Override
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public void update(OrderDTO entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<OrderDTO> findAllByCustomerId(long id, Pageable pageable) {
       Page<OrderEntity> orders= orderService.findAllByCustomerId(id,pageable);
        List<OrderEntity> content = orders.getContent();
        List<OrderDTO> orderDTOS = orderMapper.toDTOList(orders.getContent());
        return new PageImpl<>(orderDTOS,pageable,orders.getTotalElements());
    }
}
