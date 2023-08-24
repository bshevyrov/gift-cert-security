package com.epam.esm.service.impl;

import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.exception.order.PopularOrderNotFoundException;
import com.epam.esm.persistence.dao.CustomerDAO;
import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Used  to manipulate Order objects and collecting data.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final CustomerDAO customerDAO;
    private final GiftCertificateDAO giftCertificateDAO;
    private final MessageSource messageSource;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, CustomerDAO customerDAO, GiftCertificateDAO giftCertificateDAO, MessageSource messageSource) {
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
        this.giftCertificateDAO = giftCertificateDAO;
        this.messageSource = messageSource;
    }

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
        return orderDAO.findAllByCustomerEntityId(id, pageable);
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
        return orderDAO.getPopularTagInOrderByCustomerId(id).orElseThrow(() ->
                new PopularOrderNotFoundException(messageSource.getMessage("popular.order.notfound.exception",
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
        OrderEntity entity = createOrderEntityFromMap(purchase);

        double sum = 0;
        if (!customerDAO.findById(CustomerEntity.class, entity.getCustomerEntity().getId()).isPresent()) {
            throw new CustomerNotFoundException(messageSource.getMessage("customer.notfound.exception",
                    new Object[]{entity.getCustomerEntity().getId()},
                    LocaleContextHolder.getLocale()));
        }
        entity.getOrderItemEntities().forEach(orderItem -> orderItem.setOrderEntity(entity));
        for (OrderItemEntity orderItemEntity : entity.getOrderItemEntities()) {
            Optional<GiftCertificateEntity> currentGiftCertificate = giftCertificateDAO.findById(GiftCertificateEntity.class, orderItemEntity
                    .getGiftCertificateEntity().getId());

            orderItemEntity.setGiftCertificateEntity(currentGiftCertificate.orElseThrow(() ->
                    new GiftCertificateNotFoundException(messageSource.getMessage("gift.certificate.notfound.exception",
                            new Object[]{orderItemEntity.getGiftCertificateEntity().getId()},
                            LocaleContextHolder.getLocale()))));

            sum += currentGiftCertificate.get().getPrice() * orderItemEntity.getQuantity();
        }
        entity.setCost(sum);
        entity.getCustomerEntity().setOrderEntities(new ArrayList<OrderEntity>() {{
            add(entity);
        }});
        return orderDAO.create(entity);
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
        List<OrderItemEntity> orderItemEntities = (List<OrderItemEntity>) purchase.get("orderItemEntities");

        orderItemEntities.forEach(
                orderItemDTO -> orderItemDTO.setOrderEntity(orderEntity));
        orderEntity.setOrderItemEntities(orderItemEntities);
        orderItemEntities.forEach(orderItemDTO -> orderItemDTO.setOrderEntity(orderEntity));

        return orderEntity;

    }
}
