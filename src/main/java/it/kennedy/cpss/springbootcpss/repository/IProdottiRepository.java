package it.kennedy.cpss.springbootcpss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;

@Repository
public interface IProdottiRepository extends JpaRepository<ProdottiDao, Integer> {

    ProdottiDao findByAsin(String asin);

    @Query("SELECT COUNT(p) FROM ProdottiDao p")
    int countProdotti();

    Integer getStockByAsin(String asin);
}
