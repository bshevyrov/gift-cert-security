package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.AbstractDTO;
import com.epam.esm.veiw.dto.BaseDTO;

import java.util.List;

public interface BaseFacade<E extends AbstractDTO> {
    E create(E entity);

    E findById(long id);

    List<E> findAll();

    void update(E entity);

    void delete(long id);
}
