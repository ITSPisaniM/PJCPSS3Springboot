package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersItemsRepository extends JpaRepository<OrdersItemsDao, Integer> {

    List<OrdersItemsDao> findByAmazonOrderId(String amazonOrderId);

    @Query(value="SELECT SUM(quantity_ordered) FROM tordersitems o", nativeQuery = true)
    int totQuantita();

    @Query(value="SELECT SUM(item_price_amount) FROM tordersitems o", nativeQuery = true)
    int totRicavi();

}
