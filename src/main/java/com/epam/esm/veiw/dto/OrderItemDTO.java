package com.epam.esm.veiw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemDTO extends AbstractDTO {
    @JsonProperty(value = "giftCertificate")
    @Valid
    private GiftCertificateDTO giftCertificateDTO;
    @Valid
    private OrderDTO orderDTO;
    @Positive(message = "Quantity can`t be negative.")
    private int quantity;
}
