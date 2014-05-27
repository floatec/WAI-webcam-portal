package dao;

import java.util.List;
import model.Cam;
import model.Picture;

public interface PictureDao {
	public void save(Picture picture) throws Exception;
	public Picture getPicture(Long id);
	public List<Picture> list();
	public List<Picture> listByCam(long camId,String date);
}
