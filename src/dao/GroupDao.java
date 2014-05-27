package dao;

import java.util.List;

import model.Group;
import model.User;
import model.UserInGroup;

public interface GroupDao {
	public void saveUsersToGroup(Group group,  List<User> userList);
	public List<Group> list();
	public List<UserInGroup> listUserInGroup(Long id);
}

