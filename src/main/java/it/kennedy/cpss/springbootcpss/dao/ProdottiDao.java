package it.kennedy.cpss.springbootcpss.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "titems")
@Data
@Getter
@Setter
public class ProdottiDao {

    @Id
    @Column(name = "asin")
    private String asin;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "brand")
    private String brand;


}
