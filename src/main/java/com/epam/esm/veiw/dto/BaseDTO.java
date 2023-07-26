package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * BaseDTO is the superclass to all DtoRequest entities
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseDTO extends AbstractDTO {
    @NonNull
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;
}
