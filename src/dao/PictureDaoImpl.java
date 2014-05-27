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
import model.Picture;

public class PictureDaoImpl implements PictureDao {

	final JndiFactory jndi = JndiFactory.getInstance();

	@Override
	public void save(Picture picture) throws Exception{
		Connection connection = null;
		try {
			connection = jndi.getConnection("jdbc/postgres");
			PreparedStatement pstmt = connection
					.prepareStatement("insert into \"public\".\"picture\" ( \"path\", \"cam_id\") values ( ?, ?)");
			pstmt.setString(1,picture.getPath() );
			pstmt.setLong(2,picture.getCamId() );
			 pstmt.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<Picture> list() {

		List<Picture> pictureList = new ArrayList<Picture>();

		Connection connection = null;
		try {
			connection = jndi.getConnection("jdbc/postgres");

			PreparedStatement pstmt = connection
					.prepareStatement("select id,path,cam_id,time from picutre  ORDER BY cam,id");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Picture picutre = new Picture();
				picutre.setId(rs.getLong("id"));
				picutre.setPath(rs.getString("path"));
				picutre.setTimestamp(rs.getString("time"));
				picutre.setCamId(rs.getLong("cam_id"));
				pictureList.add(picutre);
			}

			return pictureList;

		} catch (Exception e) {
			throw new CamNotFoundException();
		} finally {
			closeConnection(connection);
		}
	}
	public List<Picture> listByCam(long camId,String date) {

		List<Picture> pictureList = new ArrayList<Picture>();

		Connection connection = null;
		try {
			connection = jndi.getConnection("jdbc/postgres");

			PreparedStatement pstmt = connection
					.prepareStatement("select id,path,cam_id,time from picture where cam_id = ? AND time BETWEEN to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') AND to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') ORDER BY id");
			pstmt.setLong(1,camId );
			pstmt.setString(2,date +" 00:00:00" );
			pstmt.setString(3,date +" 23:59:59" );
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Picture picutre = new Picture();
				picutre.setId(rs.getLong("id"));
				picutre.setPath(rs.getString("path"));
				picutre.setTimestamp(rs.getString("time"));
				picutre.setCamId(rs.getLong("cam_id"));
				pictureList.add(picutre);
			}

			return pictureList;

		} catch (Exception e) {
			throw new CamNotFoundException();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public Picture getPicture(Long id) {
		Picture picture = new Picture();

		return picture;
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
