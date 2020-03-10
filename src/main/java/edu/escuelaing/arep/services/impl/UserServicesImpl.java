package edu.escuelaing.arep.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.model.User;
import edu.escuelaing.arep.services.UserServices;

public class UserServicesImpl implements UserServices {
	
	private HashMap<String, User> users;	
	
	public UserServicesImpl() {
		users = new HashMap<String, User>();
		User user = new User("Valentina", "siabatto", "vale.siabatto@mail.com");
		users.put(user.getEmail(), user);
		user = new User("Andres", "Villamil", "andres.villamil@mail.com");
		users.put(user.getEmail(), user);
		user = new User("willson", "melo", "willson.melo@mail.com");
		users.put(user.getEmail(), user);
	}

	@Override
	public User add(User user) {
		String email = user.getEmail();
		users.put(email, user);		
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> usersList = new ArrayList<User>();
		for(Map.Entry user: users.entrySet()) {
			usersList.add((User) user.getValue());
		}		
		return usersList;
	}
	
	/*
	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	@Override
	public User getByEmail(String email) {
		User user = users.get(email);
		return user;
	}

}
