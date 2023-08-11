package com.epam.esm.veiw.dto;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
