package it.kennedy.cpss.springbootcpss.dao;

import javax.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.kennedy.cpss.springbootcpss.config.UserDetailCustoms;

import java.util.Collection;

@Table(name = "tusers")
@Entity
@Data
public class UtentiDao implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	private int userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	private String newToken;

	public String getNewToken() {
		return newToken;
	}

	public void setNewToken(String newToken) {
		this.newToken = newToken;
	}

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
