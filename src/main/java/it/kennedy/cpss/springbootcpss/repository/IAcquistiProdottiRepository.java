package it.kennedy.cpss.springbootcpss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;

@Repository
public interface IAcquistiProdottiRepository extends JpaRepository<AcquistiProdottiDao, Integer> {

    List<AcquistiProdottiDao> findByPurchaseId(int purchaseId);

    void deleteByPurchaseId(int id);

}
