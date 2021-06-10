package it.kennedy.cpss.springbootcpss.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table (name = "TUsers")
@Entity
@Data
public class UtentiDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "UserID")
	private int UserID;
	
	@Column (name = "Username")
	private String Username;
	
	@Column (name = "Password")
	private String Password;
	
}
