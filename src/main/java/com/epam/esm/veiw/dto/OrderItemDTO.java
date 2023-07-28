package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemDTO extends AbstractDTO{
    private int quantity;
    private GiftCertificateDTO giftCertificateDTO;
}
