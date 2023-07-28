package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public abstract class AbstractDTO {
    @Nullable
    private long id;
}
