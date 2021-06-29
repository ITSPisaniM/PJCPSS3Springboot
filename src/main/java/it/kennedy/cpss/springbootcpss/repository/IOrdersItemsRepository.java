package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersItemsRepository extends JpaRepository<OrdersItemsDao, Integer> {

    List<OrdersItemsDao> findByAmazonOrderId(String amazonOrderId);

    @Query(value="SELECT SUM(quantity_ordered) FROM tordersitems o WHERE promotion_ids = '2021-06-02'", nativeQuery = true)
    int totQuantita();

    @Query(value="SELECT SUM(item_price_amount) FROM tordersitems o WHERE promotion_ids = '2021-06-02'", nativeQuery = true)
    int totRicavi();

}
