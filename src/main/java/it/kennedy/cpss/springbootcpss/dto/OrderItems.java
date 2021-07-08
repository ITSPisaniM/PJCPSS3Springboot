package it.kennedy.cpss.springbootcpss.dto;

import lombok.Data;

@Data
public class OrderItems {

    private OrdiniItemsInternal[] orderItems;

    @Data
    public static class OrdiniItemsInternal {
        private String orderItemId;
        private String amazonOrderId;
        private String asin;
        private String title;
        private int quantityOrdered;
        private int quantityShipped;
        private int pointsGrantedPointsNumber;
        private String pointsGrantedPointsMonetaryValueCurrencyCode;
        private int pointsGrantedPointsMonetaryValueAmount;
        private String itemPriceCurrencyCode;
        private double itemPriceAmount;
        private String shippingPriceCurrencyCode;
        private int shippingPriceAmount;
        private String promotionIds;
    }
}
