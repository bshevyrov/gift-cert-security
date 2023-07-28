package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerFacade extends BaseFacade<CustomerDTO> {
    Page<CustomerDTO> findAll(Pageable pageable);
}
