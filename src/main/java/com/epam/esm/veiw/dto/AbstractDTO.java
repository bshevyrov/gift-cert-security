package com.epam.esm.veiw.dto;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDTO {
    private long id;

    public AbstractDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
