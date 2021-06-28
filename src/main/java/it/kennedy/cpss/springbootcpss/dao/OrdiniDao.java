package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "torders")
@Data
public class OrdiniDao {

	@Id
	@Column(name = "amazon_order_id")
	private String amazonOrderId;

	@Column(name = "purchase_date")
	private LocalDateTime purchaseDate;

	@Column(name = "last_update_date")
	private LocalDateTime lastUpdatedDate;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "fulfillment_channel")
	private String fullfillmentChannel;

	@Column(name = "number_of_items_shipped")
	private int numberOfItemsShipped;

	@Column(name = "number_of_items_unshipped")
	private int numebrOfItemsUnshipped;

	@Column(name = "payment_method")
	private String patmentMethod;

	@Column(name = "payment_method_details")
	private String paymentMethodDetails;

	@Column(name = "marketplace_id")
	private String marketplaceId;

	@Column(name = "shipment_service_level_category")
	private String shipmentServiceLevelCategory;

	@Column(name = "order_type")
	private String orderType;

	@Column(name = "earliest_ship_date")
	private LocalDateTime earliestsShipDate;

	@Column(name = "latest_ship_date")
	private LocalDateTime latestShipDate;

	@Column(name = "is_business_order")
	private boolean isBusinessOrder;

	@Column(name = "is_prime")
	private boolean isPrime;

	@Column(name = "is_global_express_enabled")
	private boolean isGlobalExpressEnabled;

	@Column(name = "is_premium_order")
	private boolean isPremiumOrder;

	@Column(name = "is_sold_by_ab")
	private boolean isSoldByAb;

	@Column(name = "company_legal_name")
	private String companyLegalName;

	@Column(name = "buyer_email")
	private String buyerEmail;

	@Column(name = "buyer_name")
	private String buyerName;

	@Column(name = "purchase_order_number")
	private String purchaseOrderNumber;

	@Column(name = "shipping_address_name")
	private String shippingAddressName;

	@Column(name = "shipping_address_line1")
	private String shippingAddressLine1;

	@Column(name = "shipping_address_city")
	private String shippingAddressCity;

	@Column(name = "shipping_city_state_or_region")
	private String shippingCityStateOrRegion;

	@Column(name = "shipping_state_or_region_postal_code")
	private String shippingStateOrRegionPostalCode;
}
