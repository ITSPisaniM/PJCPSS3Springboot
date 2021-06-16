package it.kennedy.cpss.springbootcpss.Repository;

import it.kennedy.cpss.springbootcpss.Dao.UtentiDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtentiRepository extends JpaRepository<UtentiDao, Integer> {
	
    @Query(value = "select * from TUsers where username = ?1 and password = ?2", nativeQuery = true)    
    UtentiDao getByUsernameAndPassword(String username, String password);
}
