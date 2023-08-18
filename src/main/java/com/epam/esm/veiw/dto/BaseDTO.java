package com.epam.esm.veiw.dto;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.Purchase;
import com.epam.esm.util.validation.group.TagCreateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * BaseDTO is the superclass to all DtoRequest entities
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class BaseDTO extends AbstractDTO {
    @Null(groups = Purchase.class)
    @NotEmpty(groups = {TagCreateValidationGroup.class,
            GiftCertificateCreateValidationGroup.class},
            message = "Name can`t be empty.")
    @Pattern(regexp = "[a-zA-Z]+", message = "Invalid character in name.")
    private String name;
}
