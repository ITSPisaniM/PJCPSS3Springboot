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

	public int amazonOrderId;

	public Date purchaseDate;

	public Date lastUpdatedDate;

	public String orderSatus;

	public String fullfillmentChannel;

	public int numberOfItemsShipped;

	public int numebrOfItemsUnshipped;

	public String patmentMethod;

	public String paymentMethodDetails;

	public String marketplaceId;

	public String shipmentServiceLevelCategory;

	public String orderType;

	public Date earliestsShipDate;

	public Date latestShipDate;

	public boolean isBusinessOrder;

	public boolean isPrime;

	public boolean isGlobalExpressEnabled;

	public boolean isPremiumOrder;

	public boolean isSoldByAb;

	public String companyLegalName;

	public String buyerEmail;

	public String buyerName;

	public String purchaseOrderNumber;

	public String shippingAddressName;

	public Date shippingAddressLine1;

	public Date shippingAddressCity;

	public Date shippingCityStateOrRegion;

	public Date shippingStateOrRegionPostalCode;

}
