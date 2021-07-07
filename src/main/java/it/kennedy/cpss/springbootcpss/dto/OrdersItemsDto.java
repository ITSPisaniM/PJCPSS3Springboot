package it.kennedy.cpss.springbootcpss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersItemsDto {

    private String orderItemId;
    private String amazonOrderId;
    private String asin;
    private String title;
    private String quantityOrdered;
    private String quantityShipped;
    private String pointsGrantedPointsNumber;
    private String pointsGrantedPointsMonetaryValueCurrencyCode;
    private String pointsGrantedPointsMonetaryValueAmount;
    private String itemPriceCurrencyCode;
    private String itemPriceAmount;
    private String shippingPriceCurrencyCode;
    private String shippingPriceAmount;
    private String promotionIds;

}
