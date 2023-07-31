package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.repository.OrderItemRepository;
import com.epam.esm.persistence.repository.OrderRepository;
import com.epam.esm.service.OrderItemService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final GiftCertificateRepository giftCertificateRepository;
    private final MessageSource messageSource;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, GiftCertificateRepository giftCertificateRepository, MessageSource messageSource) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.giftCertificateRepository = giftCertificateRepository;
        this.messageSource = messageSource;
    }

    @Override
    public OrderItemEntity create(OrderItemEntity entity) {
        return null;
    }

    @Override
    public OrderItemEntity findById(long id) {
        return null;
    }

    @Override
    public List<OrderItemEntity> findAll() {
        return null;
    }

    @Override
    public void update(OrderItemEntity entity) {

    }

    @Override
    public void delete(long id) {

    }

    public List<OrderItemEntity> createAll(List<OrderItemEntity> orderItemEntities) {
        OrderEntity save = orderRepository.save(orderItemEntities.get(0).getOrderEntity());

        orderItemEntities.forEach(
                orderItemEntity -> {
                    orderItemEntity.setGiftCertificateEntity(giftCertificateRepository.findById(orderItemEntity.getGiftCertificateEntity().getId())
                            .orElseThrow(() ->
                                    new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                                            new Object[]{orderItemEntity.getGiftCertificateEntity().getId()},
                                            LocaleContextHolder.getLocale()))));
                    orderItemEntity.setOrderEntity(save);
                });

        List<OrderItemEntity> collect = orderItemEntities.stream()
                .map(orderItemEntity -> {

                    orderItemEntity.getOrderEntity()
                            .setCost(orderItemEntities.stream()
                                    .reduce(0.0,
                                            (aDouble, orderItem) -> aDouble + orderItem.getGiftCertificateEntity().getPrice() * orderItem.getQuantity(),
                                            Double::sum));
                    return orderItemEntity;
                })
                .collect(Collectors.toList());

        return StreamSupport.stream(orderItemRepository.saveAll(collect).spliterator(), false)
                .collect(Collectors.toList());
    }
}
