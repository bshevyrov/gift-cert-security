package com.epam.esm.veiw.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * BaseDTO is the superclass to all DtoRequest entities
 */
public abstract class BaseDTO extends AbstractDTO {

    private String name;

    public BaseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
