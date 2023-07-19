package com.epam.esm.veiw.dto;

public class OrderItemDTO extends AbstractDTO{
    private int quantity;
    private GiftCertificateDTO giftCertificateDTO;

    public OrderItemDTO() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public GiftCertificateDTO getGiftCertificateDTO() {
        return giftCertificateDTO;
    }

    public void setGiftCertificateDTO(GiftCertificateDTO giftCertificateDTO) {
        this.giftCertificateDTO = giftCertificateDTO;
    }
}
