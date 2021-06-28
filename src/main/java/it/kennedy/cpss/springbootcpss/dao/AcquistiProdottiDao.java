package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tpurchasesitems")
@Data
public class AcquistiProdottiDao {

    @Id
    @Column(name = "purchases_items_id")
    private Integer purchasesItemsId;

    @ManyToOne
    @JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(name = "tpurchases_fk"))
    private AcquistiDao purchaseId;

    @Column(name = "asin")
    private String asin;

    @Column(name = "purchased_amount")
    private Integer purchasedAmount;

    @Column(name = "unit_price")
    private Double unitPrice;


}
