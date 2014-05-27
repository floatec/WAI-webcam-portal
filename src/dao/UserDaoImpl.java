package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CamNotFoundException;
import exception.CamNotSavedException;
import exception.CamNotToggledException;
import exception.UserNotDeletedException;
import exception.UserNotFoundException;
import exception.UserNotSavedException;
import jndi.JndiFactory;
import model.Cam;
import model.CamToUser;
import model.User;

public class UserDaoImpl implements UserDao {
	
	final JndiFactory jndi = JndiFactory.getInstance();
	public static final int ITERATION = 999;
	
	@Override
	public void save(User user, String[] cams) {
		if (user == null)
			throw new IllegalArgumentException("user can not be null");
		
		Connection connection = null;	
		
		
		String saltValue = new BigInteger(10, new SecureRandom()).toString(5);
		String password = user.getPassword();
		user.setSaltValue(saltValue);
		
		if (!password.isEmpty()){
		
			for (int i = 0; i < ITERATION; i++) {
				password = sha256(password+saltValue);
			}
			user.setPassword(password);
		}
		
		try {
			connection = jndi.getConnection("jdbc/postgres");
			PreparedStatement pstmt;
			if (user.getId() == null) {
				pstmt = connection.prepareStatement("insert into \"user\" (username, password, saltvalue) values (?,?,?)");
				pstmt.setString(3, user.getSaltValue());
				pstmt.setString(2, user.getPassword());
			} else {
				if (!user.getPassword().trim().isEmpty()) {
					pstmt = connection.prepareStatement("update \"user\"  set username = ?, password = ?, saltvalue = ? where id = ?");
					pstmt.setString(2, user.getPassword());
					pstmt.setString(3, user.getSaltValue());
					pstmt.setLong(4, user.getId());
				} else {
					pstmt = connection.prepareStatement("update \"user\"  set username = ? where id = ?");
					pstmt.setLong(2, user.getId());
				}
			}
			pstmt.setString(1, user.getUsername());
			pstmt.executeUpdate();
			
			pstmt = connection.prepareStatement("delete from camtouser where userid = ?");
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
			
			for (String cam : cams) {
				Long camId = Long.parseLong(cam);
				pstmt = connection.prepareStatement("insert into camtouser (userid, camid) values (?, ?)");
				pstmt.setLong(1, user.getId());
				pstmt.setLong(2, camId);
				pstmt.executeUpdate();
			}
			
			
			
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}		
	}

	@Override
	public User getUser(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			PreparedStatement pstmt = connection.prepareStatement("select * from \"user\" where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			} else {
				throw new UserNotFoundException(id);
			}			
		} catch (Exception e) {
			throw new UserNotFoundException(id);
		} finally {	
			closeConnection(connection);
		}
	}
	@Override
	public User getUser(String name) {
		if (name == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			PreparedStatement pstmt = connection.prepareStatement("select * from \"user\" where username = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSaltValue(rs.getString("saltvalue"));
				return user;
			} else {
				throw new UserNotFoundException(name);
			}			
		} catch (Exception e) {
			throw new UserNotFoundException(name);
		} finally {	
			closeConnection(connection);
		}
	}

	@Override
	public void deleteUser(Long id) {
		
		Connection connection = null;	
		
		try {
			connection = jndi.getConnection("jdbc/postgres");
			PreparedStatement pstmt;
			if (id != null) {
				pstmt = connection.prepareStatement("delete from \"user\" where id = ?");
				pstmt.setLong(1, id);
				if(pstmt.executeUpdate() == 0){
					throw new UserNotDeletedException(id);
				}
			} else {
				throw new UserNotFoundException(id);			
			}
		} catch (Exception e) {
			throw new UserNotDeletedException(id);
		} finally {
			closeConnection(connection);
		}	
	}

	@Override
	public List<User> list() {
		
	List<User> userList = new ArrayList<User>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("select * from \"user\" order by id asc");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSaltValue(rs.getString("saltValue"));
					userList.add(user);
				}			
			
			return userList;
			
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
	
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}

	@Override
	public List<CamToUser> getUserCams(Long id) {
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
				
				// Einmal �ber alle Listen iterieren. Vergleichen mit den Cams die der User sehen darf.
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
}
