package it.kennedy.cpss.springbootcpss.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table (name = "tusers")
@Entity
@Data
public class UtentiDao implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "UserID")
	private int UserID;
	
	@Column (name = "username")
	private String Username;
	
	@Column (name = "password")
	private String Password;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
