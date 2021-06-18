package it.kennedy.cpss.springbootcpss.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniDto {

	private int amazonOrderId;

	private Date purchaseDate;

	private Date lastUpdatedDate;

	private String orderSatus;

	private String fullfillmentChannel;

	private int numberOfItemsShipped;

	private int numebrOfItemsUnshipped;

	private String paymentMethod;

	private String paymentMethodDetails;

	private String marketplaceId;

	private String shipmentServiceLevelCategory;

	private String orderType;

	private Date earliestsShipDate;

	private Date latestShipDate;

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

}
