package edu.escuelaing.arep.services;

import java.util.List;

import edu.escuelaing.arep.model.User;

public interface UserServices {
	
	public User add(User user);
	
	public List<User> getAll();
	
	public User getById(long id);
	
	public void delete(long id);
	
	//public User getByEmail(String email);	

}
