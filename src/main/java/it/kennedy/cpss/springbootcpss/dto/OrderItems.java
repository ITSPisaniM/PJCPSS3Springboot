package it.kennedy.cpss.springbootcpss.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItems {
    public Ordine[] OrderItems;

    public static class Ordine {

        public String OrderItemId;
        public String AmazonOrderId;
        public String ASIN;
        public String Title;
        public String QuantityOrdered;
        public String QuantityShipped;
        public String PointsGrantedPointsNumber;
        public String PointsGrantedPointsMonetaryValueCurrencyCode;
        public String PointsGrantedPointsMonetaryValueAmount;
        public String ItemPriceCurrencyCode;
        public String ItemPriceAmount;
        public String ShippingPriceCurrencyCode;
        public String ShippingPriceAmount;
        public String PromotionIds;


    }

}