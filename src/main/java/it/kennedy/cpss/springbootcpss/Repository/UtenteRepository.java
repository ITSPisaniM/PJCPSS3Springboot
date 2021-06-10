package it.kennedy.cpss.springbootcpss.Repository;

import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<UtentiDao, Integer> {
}
