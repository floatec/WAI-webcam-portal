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
import exception.UserNotFoundException;
import jndi.JndiFactory;
import model.CamToUser;
import model.User;
import model.UserInGroup;
import model.Group;

public class GroupDaoImpl implements GroupDao  {
	
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void saveUsersToGroup(Long group, String[] userList) {
		if (group == null)
			throw new IllegalArgumentException("group can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement("delete from usertogroup where groupid = ?");
			pstmt.setLong(1, group);
			pstmt.executeUpdate();
			
			for (String user : userList) {
				Long userID = Long.parseLong(user);
				pstmt = connection.prepareStatement("insert into usertogroup (userid, groupid) values (?, ?)");
				pstmt.setLong(1, userID);
				pstmt.setLong(2, group);
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
		
		List<UserInGroup> groupList = new ArrayList<UserInGroup>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			

				PreparedStatement pstmtAllUser = connection.prepareStatement("select id, username from \"user\" ");
				ResultSet rsAllUser = pstmtAllUser.executeQuery();
				
				PreparedStatement pstmtGroupsUsers = connection.prepareStatement("select u.username, u.id from \"user\" u join usertogroup utg on utg.userid = u.id where utg.groupid = ?");				
				pstmtGroupsUsers.setLong(1, id);
				long access = 0;
				long actualGroupId = 0;
				long accessGroupId = 0;

				while(rsAllUser.next()){		
					UserInGroup userInGroup = new UserInGroup();
					actualGroupId = rsAllUser.getLong("id");
					ResultSet rsGroupsUsers= pstmtGroupsUsers.executeQuery();
					while (rsGroupsUsers.next()) {
						accessGroupId = rsGroupsUsers.getLong("id");
						if(accessGroupId == actualGroupId){
							access = 1;
						}
					}		
					userInGroup.setName(rsAllUser.getString("username"));
					userInGroup.setAccess(access);
					userInGroup.setUserid(rsAllUser.getLong("id"));
					groupList.add(userInGroup);
					access = 0;
				}				
			return groupList;			
		} catch (Exception e) {
			throw new UserNotFoundException();
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
