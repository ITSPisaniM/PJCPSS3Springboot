package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOrdersItemsRepository extends JpaRepository<OrdersItemsDao, Integer>{

    List<OrdersItemsDao> findByAmazonOrderId(String amazonOrderId);

    @Query(value="SELECT SUM(quantity_ordered) FROM tordersitems as oi inner join torders on oi.amazon_order_id = torders.amazon_order_id WHERE torders.purchase_date >= ?1 AND torders.purchase_date < ?2", nativeQuery = true)
    Integer totQuantita(LocalDate startDate, LocalDate endDate);

    @Query(value="SELECT SUM(item_price_amount * quantity_ordered) FROM tordersitems as oi inner join torders on oi.amazon_order_id = torders.amazon_order_id WHERE torders.purchase_date >= ?1 AND torders.purchase_date < ?2", nativeQuery = true)
    Integer totRicavi(LocalDate startDate, LocalDate endDate);


}
