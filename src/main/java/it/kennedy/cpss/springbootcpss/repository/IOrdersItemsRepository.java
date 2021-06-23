package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdersItemsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersItemsRepository extends JpaRepository<OrdersItemsDao, Integer> {

    List<OrdersItemsDao> findByAmazonOrderId(String id);

}
