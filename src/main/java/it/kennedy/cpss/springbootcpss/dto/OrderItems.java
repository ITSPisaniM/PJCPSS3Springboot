package it.kennedy.cpss.springbootcpss.dto;

import lombok.Data;

@Data
public class OrderItems {

    public OrdiniItemsInternal[] OrderItems;

    @Data
    public static class OrdiniItemsInternal{
        public String OrderItemId;
        public String AmazonOrderId;
        public String ASIN;
        public String Title;
        public int QuantityOrdered;
        public int QuantityShipped;
        public int PointsGrantedPointsNumber;
        public String PointsGrantedPointsMonetaryValueCurrencyCode;
        public int PointsGrantedPointsMonetaryValueAmount;
        public String ItemPriceCurrencyCode;
        public double ItemPriceAmount;
        public String ShippingPriceCurrencyCode;
        public int ShippingPriceAmount;
        public String PromotionIds;
    }
}
