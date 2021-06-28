package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tpurchases")
@Data
public class AcquistiDao {

    @Id
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "bill_number")
    private Integer billNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "purchasesItemsId")
    private List<AcquistiProdottiDao> purchasesItemsId;

}
