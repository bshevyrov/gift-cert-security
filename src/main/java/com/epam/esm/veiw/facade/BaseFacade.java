package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.AbstractDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseFacade<E extends AbstractDTO> {
    E create(E entity);

    E findById(long id);

    Page<E> findAll(Pageable pageable);

    E update(E entity);

    void delete(long id);
}
