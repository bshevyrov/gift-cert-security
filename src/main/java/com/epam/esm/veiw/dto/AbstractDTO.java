package com.epam.esm.veiw.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public abstract class AbstractDTO {
    @Nullable
    private long id;
}
