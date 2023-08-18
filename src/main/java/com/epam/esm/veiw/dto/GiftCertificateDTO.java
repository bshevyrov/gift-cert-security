package com.epam.esm.veiw.dto;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * GiftCertificateDTO is the data class, which used for data transportation .
 */
//TODO check when update
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftCertificateDTO extends BaseDTO {
    //    @Null(groups = Purchase.class)
    @NotEmpty(groups = GiftCertificateCreateValidationGroup.class,
            message = "Description can`t be empty.")
    @Pattern(regexp = "^(?! )[A-Za-z\\s]*$", message = "Illegal chars in decription/ Only letters and whitespaces allowed.")
    private String description;
    //    @Null(groups = Purchase.class)
    @Positive(groups = GiftCertificateCreateValidationGroup.class,
            message = "Price can`t be less zero.")
    private Double price;
    //    @Null(groups = Purchase.class)
    @Positive(groups = GiftCertificateCreateValidationGroup.class,
            message = "Duration can`t be less one day.")
    private Integer duration;

    @JsonProperty("tags")
    @Valid
    private List<TagDTO> tagDTOS;

}
