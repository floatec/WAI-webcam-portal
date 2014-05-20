package dao;

import java.util.List;
import model.Cam;

public interface CamDao {
	public void save(Cam cam);
	public void getCam(Long id);
	public void toggleStatus(Long id);
	public List<Cam> list();
}
