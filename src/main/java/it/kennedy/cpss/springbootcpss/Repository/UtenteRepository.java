package it.kennedy.cpss.springbootcpss.Repository;

import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtenteRepository extends JpaRepository<UtentiDao, Integer> {
    //@Query("select * from TUsers where username = username and password = password")
    UtentiDao getByUsernameAndPassword(String username, String password);
}
