package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer>, JpaSpecificationExecutor<OrdiniDao> {

    //@Query(value = "select * from torders where amazon_order_id like \'%?1\'", nativeQuery = true)
    OrdiniDao findByAmazonOrderId(String id);

    //------------------------------------- FILTERS
    default Specification<OrdiniDao> amazonOrderId(String amazonOrderId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("amazonOrderId"), amazonOrderId);
    }

    default Specification<OrdiniDao> buyerEmail(String buyerEmail){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("buyerEmail"), buyerEmail);
    }

    default Specification<OrdiniDao> purchaseDate(Date purchaseDate){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("purchaseDate"), purchaseDate);
    }

}


