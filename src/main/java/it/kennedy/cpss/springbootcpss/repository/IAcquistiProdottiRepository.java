package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAcquistiProdottiRepository extends JpaRepository<AcquistiProdottiDao, Integer> {

    List<AcquistiProdottiDao> findByPurchaseId(int purchaseId);

    void deleteByPurchaseId(int id);

//    @Query(value="DELETE FROM tpurchasesitems WHERE purchase_id = ?1", nativeQuery = true)
//    void deleteByPurchaseId(int id);

}
