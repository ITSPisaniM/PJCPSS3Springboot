package it.kennedy.cpss.springbootcpss.dto;

import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class Orders {
    private OrdiniInternal[] orders;

    @Data
    public static class OrdiniInternal {

        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        private String amazonOrderId;
        private String purchaseDate;
        private String lastUpdateDate;
        private String orderStatus;
        private String fulfillmentChannel;
        private int numberOfItemsShipped;
        private int numberOfItemsUnshipped;
        private String paymentMethod;
        private String paymentMethodDetails;
        private String marketplaceId;
        private String shipmentServiceLevelCategory;
        private String orderType;
        private String earliestShipDate;
        private String latestShipDate;
        private boolean isBusinessOrder;
        private boolean isPrime;
        private boolean isGlobalExpressEnabled;
        private boolean isPremiumOrder;
        private boolean isSoldByAB;
        private String companyLegalName;
        private String buyerEmail;
        private String buyerName;
        private String purchaseOrderNumber;
        private String shippingAddressName;
        private String shippingAddressLine1;
        private String shippingAddressCity;
        private String shippingCityStateOrRegion;
        private String shippingStateOrRegionPostalCode;
    }
}
