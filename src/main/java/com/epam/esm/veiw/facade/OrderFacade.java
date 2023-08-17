package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderFacade extends BaseFacade<OrderDTO>{
    Page<OrderDTO> findAllByCustomerId(long id, Pageable pageable);
    OrderDTO getPopularTagInOrderByCustomerId(Long id);
}
