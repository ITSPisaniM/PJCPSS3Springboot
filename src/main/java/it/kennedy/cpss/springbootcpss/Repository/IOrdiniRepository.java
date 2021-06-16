package it.kennedy.cpss.springbootcpss.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.Dao.OrdiniDao;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer>{

}
