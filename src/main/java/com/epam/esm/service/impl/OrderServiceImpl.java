package com.epam.esm.service.impl;

import com.epam.esm.exception.order.PopularOrderNotFoundException;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.repository.OrderRepository;
import com.epam.esm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Used  to manipulate Order objects and collecting data.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
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
        validateRequestId(id);
        return orderRepository.findAllByCustomerEntity_Id(id, pageable);
    }

    /**
     * Return found order or throws {@link PopularOrderNotFoundException}.
     *
     * @param id customer id values
     * @return found {@link OrderEntity}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public OrderEntity getPopularTagInOrderByCustomerId(Long id) {
        validateRequestId(id);
        return orderRepository.getPopularTagInOrderByCustomerId(id).orElseThrow(() ->
                new PopularOrderNotFoundException(messageSource.getMessage(
                        "popular.order.notfound.exception",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * Checks if user can make request to this id.
     * <p>
     * If can`t throws {@link ResponseStatusException}  HttpStatus.NOT_FOUND }
     *
     * @param id request parameter.
     */
    private void validateRequestId(Long id) {
        if (!isAuthenticatedUser(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }
    }
}
