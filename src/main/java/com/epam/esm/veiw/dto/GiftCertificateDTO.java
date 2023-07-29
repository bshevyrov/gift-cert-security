package com.epam.esm.veiw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GiftCertificateDTO is the data class, which used for data transportation .
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftCertificateDTO extends BaseDTO {
    @NotEmpty(message = "Description can`t be empty.")
    @Pattern(regexp = "^(?! )[A-Za-z\\s]*$", message = "Illegal chars in decription/ Only letters and whitespaces allowed.")
    private String description;
    @Positive(message = "Price can`t be less zero.")
    private double price;
    @Positive(message = "Duration can`t be less one day.")
    private int duration;
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
    @JsonProperty("tags")
    @Valid
    private List<TagDTO> tagDTOS;

}
