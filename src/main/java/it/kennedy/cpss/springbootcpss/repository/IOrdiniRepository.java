package it.kennedy.cpss.springbootcpss.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer>, JpaSpecificationExecutor<OrdiniDao> {

    OrdiniDao findByAmazonOrderId(String id);

    @Query(value = "select * from torders order by purchase_date DESC limit 1", nativeQuery = true)
    Optional<OrdiniDao> getLastOrder();

    // ------------------------------------- FILTERS
    default Specification<OrdiniDao> amazonOrderId(String amazonOrderId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("amazonOrderId"), amazonOrderId);
    }

    default Specification<OrdiniDao> buyerEmail(String buyerEmail) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("buyerEmail"), buyerEmail);
    }

    default Specification<OrdiniDao> purchaseDate(LocalDateTime purchaseDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("purchaseDate"),
                purchaseDate);
    }

}
