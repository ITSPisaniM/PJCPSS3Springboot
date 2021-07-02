package it.kennedy.cpss.springbootcpss.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Data
@Getter
@Setter
public class Orders {
    public OrdiniInternal[] Orders;

    @Data
    @Getter
    @Setter
    public static class OrdiniInternal {

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        public String AmazonOrderId;

        public String PurchaseDate;

        public String LastUpdateDate;

        public String OrderStatus;

        public String FulfillmentChannel;

        public int NumberOfItemsShipped;

        public int NumberOfItemsUnshipped;

        public String PaymentMethod;

        public String PaymentMethodDetails;

        public String MarketplaceId;

        public String ShipmentServiceLevelCategory;

        public String OrderType;

        public String EarliestShipDate;

        public String LatestShipDate;

        public boolean IsBusinessOrder;

        public boolean IsPrime;

        public boolean IsGlobalExpressEnabled;

        public boolean IsPremiumOrder;

        public boolean IsSoldByAB;

        public String CompanyLegalName;

        public String BuyerEmail;

        public String BuyerName;

        public String PurchaseOrderNumber;

        public String ShippingAddressName;

        public String ShippingAddressLine1;

        public String ShippingAddressCity;

        public String ShippingCityStateOrRegion;

        public String ShippingStateOrRegionPostalCode;
    }
}
