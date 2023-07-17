package com.epam.esm.facade;

import com.epam.esm.veiw.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface CustomerFacade extends BaseFacade<CustomerDTO> {
    Page<CustomerDTO> findAll(Pageable pageable);
}
