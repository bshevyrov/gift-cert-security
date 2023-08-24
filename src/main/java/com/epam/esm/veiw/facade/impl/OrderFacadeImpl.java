package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.OrderItemMapper;
import com.epam.esm.mapper.OrderMapper;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.service.OrderService;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.OrderItemDTO;
import com.epam.esm.veiw.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Class used for conversion  {@link OrderEntity} and {@link OrderDTO}.
 */
@Component
public class OrderFacadeImpl implements OrderFacade {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;

    @Autowired
    public OrderFacadeImpl(OrderMapper orderMapper, OrderItemMapper orderItemMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderService = orderService;
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */

    @Override
    @Deprecated
    public OrderDTO create(OrderDTO orderDTO) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public OrderDTO findById(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public Page<OrderDTO> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public OrderDTO update(OrderDTO entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method produces {@Page} of {@link OrderDTO} object, consumes customer id.
     *
     * @param id       request parameter
     * @param pageable pagination object
     * @return page of dtos
     */
    @Override
    public Page<OrderDTO> findAllByCustomerId(long id, Pageable pageable) {
        Page<OrderEntity> orders = orderService.findAllByCustomerId(id, pageable);
        return new PageImpl<>(orderMapper.toDTOList(orders.getContent()), pageable, orders.getTotalElements());
    }

    /**
     * Consumes customer id, produces OrderDTO.
     *
     * @param id request parameter
     * @return {@link OrderDTO}
     */
    @Override
    public OrderDTO getPopularTagInOrderByCustomerId(Long id) {
        OrderEntity popularTagInOrderByCustomerId = orderService.getPopularTagInOrderByCustomerId(id);
        return orderMapper.toDTO(popularTagInOrderByCustomerId);
    }

    /**
     * Method consumes map of parameters and return created order.
     *
     * @param purchase Map of custiomerId and List orderItemDTOS
     * @return {@link OrderDTO}
     */
    @Override
    public OrderDTO createPurchase(Map<String, Object> purchase) {
        purchase.put("orderItemEntities", orderItemMapper.toEntityList((List<OrderItemDTO>) purchase.get("orderItemDTOS")));
        purchase.remove("orderItemDTOS");
        return orderMapper.toDTO(orderService.createPurchase(purchase));
    }
}
