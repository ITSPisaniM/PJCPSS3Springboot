package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tpurchases")
@Data
public class AcquistiDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "bill_number")
    private int billNumber;

    //@OneToMany(mappedBy = "purchasesItemsId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<AcquistiProdottiDao> purchasesItemsId  = new ArrayList<>();

}
