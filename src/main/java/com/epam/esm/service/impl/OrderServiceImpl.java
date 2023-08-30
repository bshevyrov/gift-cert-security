package com.epam.esm.service.impl;

import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.exception.order.PopularOrderNotFoundException;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.persistence.repository.OrderRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Used  to manipulate Order objects and collecting data.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GiftCertificateService giftCertificateService;
    private final MessageSource messageSource;


    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public OrderEntity create(OrderEntity entity) {
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
    public OrderEntity findById(long id) {
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
    public Page<OrderEntity> findAll(Pageable pageable) {
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
    public OrderEntity update(OrderEntity entity) {
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
    public OrderEntity delete(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns all found orders of customer.
     *
     * @param id       customer id values
     * @param pageable pagination object.
     * @return {@link Page} of {@link OrderEntity}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable) {
        if (!isAuthenticatedUser(id)) {
            //Todo
            throw new JwtException("ERROR");
        }
        return orderRepository.findAllByCustomerEntity_Id(id, pageable);
    }

    /**
     * Return found order or thкows exception.
     *
     * @param id customer id values
     * @return found {@link OrderEntity}
     * @throws {@link PopularOrderNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public OrderEntity getPopularTagInOrderByCustomerId(Long id) {
        if (!isAuthenticatedUser(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        return orderRepository.getPopularTagInOrderByCustomerId(id).orElseThrow(() ->
                new PopularOrderNotFoundException(messageSource.getMessage(
                        "popular.order.notfound.exception",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * The method prepares {@link OrderEntity} to be stored in the database.
     * Checks if CustomerEntity and GiftCertificateEntity exist.
     * Sets for each OrderItemEntity GiftCertificateEntity, calculates and sets the value of the order.
     * Sets OrderEntity to CustomerEntity.
     *
     * @param purchase parameters of order
     * @return saved entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public OrderEntity createPurchase(Map<String, Object> purchase) {

        OrderEntity orderEntity = createOrderEntityFromMap(purchase);
        if (!isAuthenticatedUser(orderEntity.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        double sum = 0;
        if (!customerRepository.findById(orderEntity.getCustomerEntity().getId()).isPresent()) {
            throw new CustomerNotFoundException(
                    messageSource.getMessage("customer.notfound.exception",
                            new Object[]{"id - " + orderEntity.getCustomerEntity().getId()},
                            LocaleContextHolder.getLocale()));
        }
        orderEntity.getOrderItemEntities()
                .forEach(orderItem -> orderItem.setOrderEntity(orderEntity));
        for (OrderItemEntity orderItemEntity : orderEntity.getOrderItemEntities()) {
            Long giftCertificateId = orderItemEntity.getGiftCertificateEntity().getId();

            GiftCertificateEntity currentGiftCertificate =
                    giftCertificateService.findById(giftCertificateId);

            orderItemEntity.setGiftCertificateEntity(currentGiftCertificate);

            sum += currentGiftCertificate.getPrice() * orderItemEntity.getQuantity();
        }
        orderEntity.setCost(sum);
        orderEntity.getCustomerEntity()
                .setOrderEntities(new ArrayList<OrderEntity>() {{
                    add(orderEntity);
                }});
        return orderRepository.save(orderEntity);
    }


    /**
     * Transforms Map to {@link OrderEntity}
     *
     * @param purchase lost containing customerId and List of orderItemEntities
     * @return OrderEntity
     */
    private OrderEntity createOrderEntityFromMap(Map<String, Object> purchase) {
        OrderEntity orderEntity = new OrderEntity();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId((Long) purchase.get("customerId"));
        orderEntity.setCustomerEntity(customerEntity);
        List<OrderItemEntity> orderItemEntities =
                (List<OrderItemEntity>) purchase.get("orderItemEntities");

        orderItemEntities.forEach(
                orderItemDTO -> orderItemDTO.setOrderEntity(orderEntity));
        orderEntity.setOrderItemEntities(orderItemEntities);
        orderItemEntities.forEach(
                orderItemDTO -> orderItemDTO.setOrderEntity(orderEntity));

        return orderEntity;

    }

}
