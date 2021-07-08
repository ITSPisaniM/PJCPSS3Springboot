package it.kennedy.cpss.springbootcpss.dao;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

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

}
