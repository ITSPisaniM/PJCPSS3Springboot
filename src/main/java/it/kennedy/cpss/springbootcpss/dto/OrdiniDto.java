package it.kennedy.cpss.springbootcpss.dto;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniDto {

	private String amazonOrderId;

	private LocalDateTime purchaseDate;

	private LocalDateTime lastUpdatedDate;

	private String orderStatus;

	private String fullfillmentChannel;

	private int numberOfItemsShipped;

	private int numebrOfItemsUnshipped;

	private String patmentMethod;

	private String paymentMethodDetails;

	private String marketplaceId;

	private String shipmentServiceLevelCategory;

	private String orderType;

	private LocalDateTime earliestsShipDate;

	private LocalDateTime latestShipDate;

	private boolean isBusinessOrder;

	private boolean isPrime;

	private boolean isGlobalExpressEnabled;

	private boolean isPremiumOrder;

	private boolean isSoldByAb;

	private String companyLegalName;

	private String buyerEmail;

	private String buyerName;

	private String purchaseOrderNumber;

	private String shippingAddressName;

	private String shippingAddressLine1;

	private String shippingAddressCity;

	private String shippingCityStateOrRegion;

	private String shippingStateOrRegionPostalCode;

	private List<OrdersItemsDao> ordersItems;
}
