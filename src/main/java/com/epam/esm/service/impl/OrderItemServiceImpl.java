package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.dao.OrderItemDAO;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.service.OrderItemService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDAO orderItemDAO;
    private final OrderDAO orderDAO;
    private final GiftCertificateDAO giftCertificateDAO;
    private final MessageSource messageSource;

    public OrderItemServiceImpl(OrderItemDAO orderItemDAO, OrderDAO orderDAO, GiftCertificateDAO giftCertificateDAO, MessageSource messageSource) {
        this.orderItemDAO = orderItemDAO;
        this.orderDAO = orderDAO;
        this.giftCertificateDAO = giftCertificateDAO;
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
    public Page<OrderItemEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(OrderItemEntity entity) {

    }

    @Override
    public OrderItemEntity delete(long id) {
        return null;
    }

    public List<OrderItemEntity> createAll(List<OrderItemEntity> orderItemEntities) {
//        OrderEntity save = orderDAO.save(orderItemEntities.get(0).getOrderEntity());
//
//        orderItemEntities.forEach(orderItemEntity -> {
//            orderItemEntity.setGiftCertificateEntity(giftCertificateDAO.findById(orderItemEntity.getGiftCertificateEntity().getId()).orElseThrow(() -> new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion", new Object[]{orderItemEntity.getGiftCertificateEntity().getId()}, LocaleContextHolder.getLocale()))));
//            orderItemEntity.setOrderEntity(save);
//        });
//
//        List<OrderItemEntity> collect = orderItemEntities.stream().map(orderItemEntity -> {
//
//            orderItemEntity.getOrderEntity().setCost(orderItemEntities.stream().reduce(0.0, (aDouble, orderItem) -> aDouble + orderItem.getGiftCertificateEntity().getPrice() * orderItem.getQuantity(), Double::sum));
//            return orderItemEntity;
//        }).collect(Collectors.toList());
//
//        return StreamSupport.stream(orderItemDAO.saveAll(collect).spliterator(), false).collect(Collectors.toList());
   return null;
    }
}
