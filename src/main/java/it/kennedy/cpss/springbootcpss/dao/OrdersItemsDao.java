package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tordersitems")
@Data
@Getter
@Setter
public class OrdersItemsDao {

    @Id
    @Column(name = "order_item_id")
    private String orderItemId;

    @Column(name = "amazon_order_id")
    private String amazonOrderId;

    @Column(name = "asin")
    private String ASIN;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity_ordered")
    private Integer quantityOrdered;

    @Column(name = "quantity_shipped")
    private Integer quantityShipped;

    @Column(name = "points_granted_points_number")
    private Double pointsGrantedPointsNumber;

    @Column(name = "points_granted_points_monetary_value_currency_code")
    private String pointsGrantedPointsMonetaryValueCurrencyCode;

    @Column(name = "points_granted_points_monetary_value_amount")
    private Double pointsGrantedPointsMonetaryValueAmount;

    @Column(name = "item_price_currency_code")
    private String itemPriceCurrencyCode;

    @Column(name = "item_price_amount")
    private Double itemPriceAmount;

    @Column(name = "shipping_price_currency_code")
    private String shippingPriceCurrencyCode;

    @Column(name = "shipping_price_amount")
    private Double shippingPriceAmount;

    @Column(name = "promotion_ids")
    private String promotionIds;

}
