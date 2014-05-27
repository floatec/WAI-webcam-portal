package dao;
import java.util.List;

import model.User;

public interface UserDao {
		public void save(User user);
		public User getUser(Long id);
		public void deleteUser(Long id);
		public List<User> list();
	}