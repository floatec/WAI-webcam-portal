package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CamNotFoundException;
import exception.CamNotSavedException;
import jndi.JndiFactory;
import model.Cam;

public class CamDaoImpl implements CamDao  {
	
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void save(Cam cam) {
		if (cam == null)
			throw new IllegalArgumentException("cam can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			if (cam.getId() == null) {
				PreparedStatement pstmt = connection.prepareStatement("insert into cam (name, url, status) values (?,?,?)");
				pstmt.setString(1, cam.getName());
				pstmt.setString(2, cam.getUrl());
				pstmt.setString(3, cam.isStatus()?"1":"0");
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = connection.prepareStatement("update cam set name = ?, url = ?, status = ? where id = ?");
				pstmt.setString(1, cam.getName());
				pstmt.setString(2, cam.getUrl());
				pstmt.setString(3, cam.isStatus()?"1":"0");
				pstmt.setLong(4, cam.getId());
				pstmt.executeUpdate();
			}			
		} catch (Exception e) {
			throw new CamNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void toggleStatus(Long id, String status) {
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
			if(status.equals("false")){
				status = "1";
			}else{
				status = "0";
			}
		
			PreparedStatement pstmt = connection.prepareStatement("update cam set status = ? where id = ?");
			pstmt.setString(1, status);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			throw new CamNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<Cam> list() {
		
		List<Cam> camList = new ArrayList<Cam>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("select * from cam order by id asc");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Cam cam = new Cam();
					cam.setId(rs.getLong("id"));
					cam.setName(rs.getString("name"));
					cam.setUrl(rs.getString("url"));
					cam.setStatus(rs.getBoolean("status"));
					camList.add(cam);
				}			
			
			return camList;
			
		} catch (Exception e) {
			throw new CamNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}

	@Override
	public Cam getCam(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			PreparedStatement pstmt = connection.prepareStatement("select * from cam where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				Cam cam = new Cam();
				cam.setId(rs.getLong("id"));
				cam.setName(rs.getString("name"));
				cam.setUrl(rs.getString("url"));
				cam.setStatus(rs.getBoolean("status"));
				return cam;
			} else {
				throw new CamNotFoundException(id);
			}			
		} catch (Exception e) {
			throw new CamNotFoundException(id);
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
	
	@Override
	public List<Cam> getCamsForuser(long id) {
List<Cam> camList = new ArrayList<Cam>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("SELECT cam.* FROM cam, camtouser WHERE  cam.id = camtouser.camid and camtouser.userid = ? order by id asc");				
				pstmt.setLong(1,id);
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Cam cam = new Cam();
					cam.setId(rs.getLong("id"));
					cam.setName(rs.getString("name"));
					cam.setUrl(rs.getString("url"));
					cam.setStatus(rs.getBoolean("status"));
					camList.add(cam);
				}			
			
			return camList;
			
		} catch (Exception e) {
			throw new CamNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}
	
}
