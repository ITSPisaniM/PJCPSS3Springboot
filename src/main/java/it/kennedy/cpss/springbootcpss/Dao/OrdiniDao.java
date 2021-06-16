package it.kennedy.cpss.springbootcpss.Dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "torders")
@Data
public class OrdiniDao {
	
	@Id
	@Column(name = "AmazonOrderId")
	private int AmazonOrderId;

	@Column(name = "BuyerEmail")
	private String BuyerEmail;

	@Column(name = "BuyerName")
	private String BuyerName;

	@Column(name = "CompanyLegalName")
	private String CompanyLegalName;

	@Column(name = "EarliestShipDate")
	private Date EarliestShipDate;

	@Column(name = "FulfillmentChannel")
	private int FulfillmentChannel;

	@Column(name = "IsBusinessOrder")
	private boolean IsBusinessOrder;

	@Column(name = "IsGlobalExpressEnabled")
	private boolean IsGlobalExpressEnabled;

	@Column(name = "IsPremiumOrder")
	private boolean IsPremiumOrder;

	@Column(name = "IsPrime")
	private boolean IsPrime;

	@Column(name = "IsSoldByAB")
	private boolean IsSoldByAB;

	@Column(name = "LastUpdateDate")
	private Date LastUpdateDate;
	
	@Column(name = "LatestShipDate")
	private Date LatestShipDate;
	
	@Column(name = "MarketplaceId")
	private String MarketplaceId;
	
	@Column(name = "NumberOfItemsShipped")
	private int NumberOfItemsShipped;
	
	@Column(name = "NumberOfItemsUnshipped")
	private int NumberOfItemsUnshipped;
	
	@Column(name = "OrderStatus")
	private String OrderStatus;
	
	@Column(name = "OrderType")
	private String OrderType;
	
	@Column(name = "PaymentMethod")
	private String PaymentMethod;
	
	@Column(name = "PaymentMethodDetails")
	private String PaymentMethodDetails;
	//rrrrr
	@Column(name = "PurchaseDate")
	private Date PurchaseDate;
	
	@Column(name = "PurchaseOrderNumber")
	private int PurchaseOrderNumber;
	
	@Column(name = "ShipmentServiceLevelCategory")
	private int ShipmentServiceLevelCategory;
	
	@Column(name = "ShippingAddressCity")
	private String ShippingAddressCity;
	
	@Column(name = "ShippingAddressLine1")
	private String ShippingAddressLine1;
	
	@Column(name = "ShippingAddressName")
	private String ShippingAddressName;
	
	@Column(name = "ShippingCityStateOrRegion")
	private String ShippingCityStateOrRegion;
	
	@Column(name = "ShippingStateOrRegionPostalCode")
	private String ShippingStateOrRegionPostalCode;

}
