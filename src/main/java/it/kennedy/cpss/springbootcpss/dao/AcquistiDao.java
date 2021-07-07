package it.kennedy.cpss.springbootcpss.dao;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tpurchases")
@Data
public class AcquistiDao {

    @Id
    @Column(name = "purchase_id")
    private int purchaseId;

    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "bill_number")
    private int billNumber;

}
