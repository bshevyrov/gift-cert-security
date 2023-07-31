package com.epam.esm.veiw.dto;

import com.epam.esm.util.validation.group.Purchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GiftCertificateDTO is the data class, which used for data transportation .
 */
//TODO check when update
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftCertificateDTO extends BaseDTO {
    @Null(groups = Purchase.class)
    @NotEmpty(message = "Description can`t be empty.")
    @Pattern(regexp = "^(?! )[A-Za-z\\s]*$", message = "Illegal chars in decription/ Only letters and whitespaces allowed.")
    private String description;
    @Null(groups = Purchase.class)
    @Positive(message = "Price can`t be less zero.")
    private double price;
    @Null(groups = Purchase.class)
    @Positive(message = "Duration can`t be less one day.")
    private int duration;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
    @JsonProperty("tags")
    @Valid
    private List<TagDTO> tagDTOS;

}
