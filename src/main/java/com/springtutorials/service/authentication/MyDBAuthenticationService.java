package com.springtutorials.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springtutorials.dao.IUserDAO;
import com.springtutorials.model.UserModel;

@Service
public class MyDBAuthenticationService implements UserDetailsService{

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel userModel = userDAO.findOneByUsername(username);
		if (userModel == null) {
			throw new UsernameNotFoundException("Not found user!!!");
		}
		
		List<String> roles = userDAO.findRoleByUsername(username);
		for (String role : roles) {
			System.out.println("role: " + role);
		}
		
		List<GrantedAuthority> grantList = new ArrayList<>();
		if (roles != null) {
			for (String role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = (UserDetails)new User(userModel.getUsername(), 
				userModel.getPassword(), grantList);
		
		return userDetails;
	}

}
