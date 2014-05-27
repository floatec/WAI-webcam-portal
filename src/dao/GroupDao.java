package dao;

import java.util.List;

import model.Group;
import model.User;
import model.UserInGroup;

public interface GroupDao {
	public void saveUsersToGroup(Long group,  String[] userList);
	public List<Group> list();
	public List<UserInGroup> listUserInGroup(Long id);
}

