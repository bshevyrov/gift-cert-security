package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemDTO extends AbstractDTO{
    private int quantity;
    private GiftCertificateDTO giftCertificateDTO;


}
