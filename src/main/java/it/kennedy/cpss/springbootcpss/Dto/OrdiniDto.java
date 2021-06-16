package it.kennedy.cpss.springbootcpss.Dto;

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

	public String CompanyLegalName;
	public Date EarliestShipDate;
	public Integer FulfillmentChannel;
	public Boolean IsBusinessOrder;
	public Boolean IsGlobalExpressEnabled;
	public Boolean IsPremiumOrder;
	public Boolean IsPrime;
	public Boolean IsSoldByAB;
	public Date LastUpdateDate;
	public Date LatestShipDate;
	public String MarketplaceId;
	public Integer NumberOfItemsShipped;
	public Integer NumberOfItemsUnshipped;
	public String OrderStatus;
	public String OrderType;
	public String PaymentMethod;
	public String PaymentMethodDetails;
	public Date PurchaseDate;
	public Integer PurchaseOrderNumber;
	public Integer ShipmentServiceLevelCategory;
	public String ShippingAddressCity;
	public String ShippingAddressLine1;
	public String ShippingAddressName;
	public String ShippingCityStateOrRegion;
	public String ShippingStateOrRegionPostalCode;
	
}
