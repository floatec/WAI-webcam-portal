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
		
		List<CamToUser> camList = new ArrayList<CamToUser>();
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
				//Alle Cams
				PreparedStatement pstmtAllCam = connection.prepareStatement("select id, name from cam");
				ResultSet rsAllCam = pstmtAllCam.executeQuery();
				
				//CamsForUser
				PreparedStatement pstmtUserCams = connection.prepareStatement("select c.name, c.id from cam c join camtouser ctu on ctu.camid = c.id where ctu.userid = ?");				
				pstmtUserCams.setLong(1, id);
				long access = 0;
				long actualCamId = 0;
				long accessCamId = 0;
				
				// Einmal über alle Listen iterieren. Vergleichen mit den Cams die der User sehen darf.
				while(rsAllCam.next()){		
					CamToUser camToUser = new CamToUser();
					actualCamId = rsAllCam.getLong("id");
					ResultSet rsUserCams = pstmtUserCams.executeQuery();
					while (rsUserCams.next()) {
						accessCamId = rsUserCams.getLong("id");
						if(accessCamId == actualCamId){
							access = 1;
						}
					}		
					camToUser.setName(rsAllCam.getString("name"));
					camToUser.setAccess(access);
					camToUser.setCamid(rsAllCam.getLong("id"));
					camList.add(camToUser);
					access = 0;
				}				
			return camList;			
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
