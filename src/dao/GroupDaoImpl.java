package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.GroupNotFoundException;
import exception.GroupNotSavedException;
import exception.GroupNotDeletedException;
import jndi.JndiFactory;
import model.User;
import model.UserInGroup;
import model.Group;

public class GroupDaoImpl implements GroupDao  {
	
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void saveUsersToGroup(Group group, List<User> userList) {
		if (group == null)
			throw new IllegalArgumentException("group can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement("delete from usertogroup where groupid = ?");
			pstmt.setLong(1, group.getId());
			if(pstmt.executeUpdate() == 0){
				throw new GroupNotDeletedException(group.getId());
			}
			for (int i = 0; i < userList.size(); i++){			
				pstmt = connection.prepareStatement("insert into usertogroup(groupid, userid) values ( ?, ?);");
				pstmt.setLong(1, group.getId());
				pstmt.setLong(2, userList.get(i).getId());
				pstmt.executeUpdate();
			}	
		} catch (Exception e) {
			throw new GroupNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<Group> list() {
		
		List<Group> groupList = new ArrayList<Group>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("select * from \"group\" order by id asc");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Group group = new Group();
					group.setId(rs.getLong("id"));
					group.setName(rs.getString("name"));
					groupList.add(group);
				}			
			
			return groupList;
			
		} catch (Exception e) {
			throw new GroupNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}

	@Override
	public List<UserInGroup> listUserInGroup( Long id ) {
		
		List<UserInGroup> userInGroupList = new ArrayList<UserInGroup>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("select u.username, utg.userid from \"user\" u left outer join usertogroup utg on utg.userid = u.id where utg.groupid = ? or utg.groupid is null order by utg.userid asc");	
				pstmt.setLong(1, id);
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					UserInGroup userInGroup = new UserInGroup();
					userInGroup.setUserName(rs.getString("name"));
					Long groupId = rs.getLong("groupid");
					if ( groupId == null){
						userInGroup.setInGroup(false);			
					} else {
						userInGroup.setInGroup(true);						
					}
					userInGroupList.add(userInGroup);
				}			
			
			return userInGroupList;
			
		} catch (Exception e) {
			throw new GroupNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}	
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// nothing to do
				e.printStackTrace();
			}				
		}
	}
	
}
