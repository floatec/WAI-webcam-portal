package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CamNotFoundException;
import jndi.JndiFactory;
import model.Cam;

public class CamDaoImpl implements CamDao  {
	
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void save(Cam cam) {
		
	}

	@Override
	public void toggleStatus(Long id) {
		
	}

	@Override
	public List<Cam> list() {
		
		List<Cam> camList = new ArrayList<Cam>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/postgres");			
			
				PreparedStatement pstmt = connection.prepareStatement("select id, name, url, status from cam");				
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
	public void getCam(Long id) {
		
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
