package it.kennedy.cpss.springbootcpss.repository;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer> {

}
