package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

}
