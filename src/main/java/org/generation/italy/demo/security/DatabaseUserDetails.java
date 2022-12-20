package org.generation.italy.demo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//implementiamo l'interfaccia UserDetails
public class DatabaseUserDetails implements UserDetails {

	//indichiamo il serialVersionUID
	

	private static final long serialVersionUID = -763145497702040355L;
	//variabile d'istanza
	private final User user;
	
	//costruttore di riferimento
	public DatabaseUserDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//recuperiamo il ruolo dell'user
		Set<Role> roles = user.getRoles();
		Set<GrantedAuthority> grantRole = new HashSet<>();
		
		for (Role role : roles) 
			//per ogni ruolo dell'user inseriamo la sua autorità
			grantRole.add(new SimpleGrantedAuthority(role.getName()));
		
		return grantRole;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
