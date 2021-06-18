package it.kennedy.cpss.springbootcpss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;

@Repository
public interface IOrdiniRepository extends JpaRepository<OrdiniDao, Integer> {

}
