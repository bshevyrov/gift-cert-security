package com.epam.esm.veiw.model;

import org.springframework.hateoas.RepresentationModel;

public class OrderItemModel extends RepresentationModel<OrderItemModel> {
    private long id;
    private int quantity;
    private GiftCertificateModel giftCertificateModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public GiftCertificateModel getGiftCertificateModel() {
        return giftCertificateModel;
    }

    public void setGiftCertificateModel(GiftCertificateModel giftCertificateModel) {
        this.giftCertificateModel = giftCertificateModel;
    }
}
