package dao;
import java.util.List;

import model.Cam;
import model.CamToUser;
import model.User;

public interface UserDao {
		public void save(User user, String[] cams);
		public User getUser(Long id);
		public User getUser(String name);
		public void deleteUser(Long id);
		public List<User> list();
		public List<CamToUser> getUserCams(Long id);
		
	}