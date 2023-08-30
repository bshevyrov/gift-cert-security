package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderFacade extends BaseFacade<OrderDTO> {
    Page<OrderDTO> findAllByCustomerId(long id, Pageable pageable);

    OrderDTO getPopularTagInOrderByCustomerId(Long id);
}
