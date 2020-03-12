package edu.escuelaing.arep.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.model.User;
import edu.escuelaing.arep.services.UserServices;

public class UserServicesImpl implements UserServices {
	
	private HashMap<Long, User> users;	

	
	public UserServicesImpl() {
		users = new HashMap<Long, User>();
		User user = new User("Valentina", "siabatto", "vale.siabatto@mail.com");
		user.setId(1);
		users.put((long) 1, user);
		user = new User("Andres", "Villamil", "andres.villamil@mail.com");
		user.setId(2);
		users.put((long) 2, user);
		user = new User("willson", "melo", "willson.melo@mail.com");
		user.setId(3);
		users.put((long) 3, user);
		
	}

	@Override
	public List<User> getAll() {
		List<User> usersList = new ArrayList<User>();
		for(Map.Entry user: users.entrySet()) {
			usersList.add((User) user.getValue());
		}		
		return usersList;
	}
	
	@Override
	public User add(User user) {
		long newId = (long) (users.size()+1);
		user.setId(newId);
		users.put(newId, user);		
		return user;
	}

	@Override
	public User getById(long id) {
		return users.get(id);
	}
	
	@Override
	public User delete(long id) {
		return users.remove(id);
	}

}
