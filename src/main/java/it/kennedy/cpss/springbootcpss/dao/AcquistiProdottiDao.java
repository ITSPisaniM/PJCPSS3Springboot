package it.kennedy.cpss.springbootcpss.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tpurchasesitems")
@Data
public class AcquistiProdottiDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchases_items_id")
    private Integer purchasesItemsId;

    @Column(name = "purchase_id")
    private int purchaseId;

    @Column(name = "asin")
    private String asin;

    @Column(name = "purchased_amount")
    private Integer purchasedAmount;

    @Column(name = "unit_price")
    private Double unitPrice;

}
