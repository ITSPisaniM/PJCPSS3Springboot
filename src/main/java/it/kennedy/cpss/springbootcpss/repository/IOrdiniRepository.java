package it.kennedy.cpss.springbootcpss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer> {

    //@Query(value = "select * from torders where amazon_order_id like \'%?1\'", nativeQuery = true)
    OrdiniDao findByAmazonOrderId(String id);

    @Query(value = "select * from torders order by purchase_date DESC limit 1", nativeQuery = true)
    Optional<OrdiniDao> getLastOrder();

}
