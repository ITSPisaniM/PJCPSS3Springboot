package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdottiRepository extends JpaRepository<ProdottiDao, Integer> {

    ProdottiDao findByAsin(String asin);

    @Query("SELECT COUNT(p) FROM ProdottiDao p")
    int countProdotti();

    @Query(value="SELECT stock FROM titems WHERE asin = ?1", nativeQuery = true)
    Integer getStockByAsin(String asin);

    @Modifying
    @Query(value="UPDATE titems SET stock = ?1 WHERE asin = ?2", nativeQuery = true)
    void setStockByAsin(int giacenzaProdotto, String asin);
}
