package com.epam.esm.veiw.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;


@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractDTO {
    @Nullable
    private long id;
}
