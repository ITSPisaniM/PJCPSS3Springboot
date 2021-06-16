package it.kennedy.cpss.springbootcpss.Repository;

import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<UtentiDao, Integer> {
    @Query(value = "select * from TUsers where username = ?1 and password = ?2", nativeQuery = true)
    UtentiDao getByUsernameAndPassword(String username, String password);

    @Query(value = "select * from tusers where username = ?1", nativeQuery = true)
    Optional<UtentiDao> findByUsername(String username);
}
