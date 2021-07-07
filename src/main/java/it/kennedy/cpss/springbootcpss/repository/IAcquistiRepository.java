package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.AcquistiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcquistiRepository extends JpaRepository<AcquistiDao, Integer> {

    AcquistiDao findByPurchaseId(int id);

    @Query(value=" SELECT MAX(purchase_id) FROM tpurchases;", nativeQuery = true)
    int getLastId();

//    @Query(value="DELETE FROM tpurchases WHERE purchase_id = ?1 ", nativeQuery = true)
//    void deleteByPurchaseId(int id);

}
