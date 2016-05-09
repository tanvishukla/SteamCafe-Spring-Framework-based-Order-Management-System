package com.TOMSystem.service;

import java.util.List;
import com.TOMSystem.User.User;

public interface UserService {
	public void add(User user);
	public void edit(User user);
<<<<<<< HEAD
	public void delete(String emailId);
	public User getUser(String emailId);
=======
	public void delete(String email);
	public User getUser(String email);
>>>>>>> 799d81ffefb0bdf9d1cec927e69fc982962b510a
	public List getAllUsers();
	
}
