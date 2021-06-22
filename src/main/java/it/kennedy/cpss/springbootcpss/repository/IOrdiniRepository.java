package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.UtentiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer> {

    //@Query(value = "select * from torders where amazon_order_id like \'%?1\'", nativeQuery = true)
    OrdiniDao findByAmazonOrderId(String id);

}
