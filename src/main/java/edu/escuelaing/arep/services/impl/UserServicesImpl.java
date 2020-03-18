package edu.escuelaing.arep.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.dao.ConnectionDao;
import edu.escuelaing.arep.model.User;
import edu.escuelaing.arep.services.UserServices;
import edu.escuelaing.arep.dao.*;

public class UserServicesImpl implements UserServices {
	
	ConnectionDao connDao;
	Connection conn;	
	
	public UserServicesImpl() {
		connDao = new ConnectionDao();
		conn = connDao.retriveConnection();				
	}

	@Override
	public List<User> getAll() {
		List<User> usersList = connDao.getUsers(conn);		
		return usersList;
	}
	
	@Override
	public User add(User user) {
		connDao.addUser(conn, user);		
		return user;
	}

	@Override
	public User getById(long id) {
		return users.get(id);
	}
	
	@Override
	public void delete(long id) {
		connDao.deleteUser(conn, id);
	}

}
