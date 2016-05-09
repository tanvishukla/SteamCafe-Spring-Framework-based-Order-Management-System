package com.TOMSystem.dao;

import java.util.List;
import com.TOMSystem.User.User;

public interface UserDao {
		public void add(User user);
		public void edit(User user);
		public void delete(String email);
		public User getUser(String email);
		public List getAllUsers();
}
