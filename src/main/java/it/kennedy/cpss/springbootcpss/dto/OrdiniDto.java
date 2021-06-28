package it.kennedy.cpss.springbootcpss.dto;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniDto {

	public String amazonOrderId;

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

	public String shippingAddressLine1;

	public String shippingAddressCity;

	public String shippingCityStateOrRegion;

	public String shippingStateOrRegionPostalCode;

	public List<OrdersItemsDao> ordersItems;
}
