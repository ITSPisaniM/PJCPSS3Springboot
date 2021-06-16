package it.kennedy.cpss.springbootcpss.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table (name = "tusers")
@Entity
@Data
public class UtentiDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "UserID")
	private int UserID;
	
	@Column (name = "username")
	private String Username;
	
	@Column (name = "password")
	private String Password;
	
}
