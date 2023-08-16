package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.dao.OrderItemDAO;
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
import java.util.Optional;

@Service

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final GiftCertificateDAO giftCertificateDAO;
    private final MessageSource messageSource;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, OrderItemDAO orderItemDAO, GiftCertificateDAO giftCertificateDAO, MessageSource messageSource) {
        this.orderDAO = orderDAO;
        this.orderItemDAO = orderItemDAO;
        this.giftCertificateDAO = giftCertificateDAO;
        this.messageSource = messageSource;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})

    public OrderEntity create(OrderEntity entity) {
     /*   //validation customer id
        double sum = 0;
entity.getOrderItemEntities().forEach(orderItem -> orderItem.setOrderEntity(entity));
        for (OrderItemEntity orderItemEntity : entity.getOrderItemEntities()) {
            Optional<GiftCertificateEntity> currentGiftCertificate = giftCertificateDAO.findById(GiftCertificateEntity.class, orderItemEntity
                    .getGiftCertificateEntity().getId());


            orderItemEntity.setGiftCertificateEntity(currentGiftCertificate      .orElseThrow(() ->
                    new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                            new Object[]{orderItemEntity.getGiftCertificateEntity().getId()},
                            LocaleContextHolder.getLocale()))));

            sum += currentGiftCertificate.get().getPrice() * orderItemEntity.getQuantity();
        }
        entity.setCost(sum);
        entity.getCustomerEntity().setOrderEntities(new ArrayList<OrderEntity>(){{add(entity);}});*/
        return orderDAO.create(entity);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public OrderEntity findById(long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public Page<OrderEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})

    public void update(OrderEntity entity) {

    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public OrderEntity delete(long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable) {
        return orderDAO.findAllByCustomerEntityId(id, pageable);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public OrderEntity getPopularTagInOrderByCustomerId(Long id) {
        Optional<OrderEntity> popularTagInOrderByCustomerId = orderDAO.getPopularTagInOrderByCustomerId(id);
        return popularTagInOrderByCustomerId.get();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public OrderEntity createOrderByOrderItems(List<OrderItemEntity> orderItemEntities) {

        double sum = 0;
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            Optional<GiftCertificateEntity> currentGiftCertificate = giftCertificateDAO.findById(GiftCertificateEntity.class, orderItemEntity
                    .getGiftCertificateEntity().getId());


            orderItemEntity.setGiftCertificateEntity(currentGiftCertificate      .orElseThrow(() ->
                    new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                            new Object[]{orderItemEntity.getGiftCertificateEntity().getId()},
                            LocaleContextHolder.getLocale()))));

            sum += currentGiftCertificate.get().getPrice() * orderItemEntity.getQuantity();
        }
        double finalSum = sum;
        OrderEntity orderEntity1 = orderItemEntities.get(0).getOrderEntity();
        orderEntity1.setCost(finalSum);
        orderEntity1.getCustomerEntity().setOrderEntities(new ArrayList<OrderEntity>(){{add(orderEntity1);}});
        OrderEntity orderEntity2 = orderDAO.create(orderEntity1);
                orderItemEntities.forEach(orderItem -> orderItem.setOrderEntity(orderEntity2));

//        entity.setCost(sum);
//        entity.getCustomerEntity().setOrderEntities(new ArrayList<OrderEntity>(){{add(entity);}});
orderItemEntities.forEach(orderItemDAO::create);


//        OrderEntity orderEntity = orderItemDAO.create(orderItemEntities.get(0)).getOrderEntity();
//
//if(orderItemEntities.size()>1){
//    for (int i = 1; i <orderItemEntities.size()-1 ; i++) {
//        orderItemEntities.get(i).getOrderEntity().setId(orderEntity.getId());
//        orderItemDAO.create(orderItemEntities.get(i));
//    }
//}

//        orderItemEntities.forEach(orderItemDAO::create);
//        return orderItemEntities.get(0).getOrderEntity();
        return orderEntity2;
    }


}
