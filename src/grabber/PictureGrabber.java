package grabber;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dao.CamDao;
import dao.DaoFactory;
import dao.PictureDao;

import model.Cam;
import model.Picture;



public class PictureGrabber implements Runnable {
	public static final String PATH = "/Users/floatec/";
	final CamDao camDao = DaoFactory.getInstance().getCamDao();
	final PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	
	

	@Override
	public void run() {
		System.out.println(11111);
		try {
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK); 
			String path = PATH +calendar.get(Calendar.YEAR) + "/" +
					(calendar.get(Calendar.MONTH)+1) + "/" +calendar.get(Calendar.DAY_OF_MONTH) + "/";
			
			List<Cam> collection = camDao.list();
			System.out.println(collection.size());
			for (Cam element : collection) {
				File file = new File(path + element.getName()+"/");
				file.mkdirs();
				System.out.println(element.getName());
				String saveTo = path + element.getName() + "/" + calendar.getTimeInMillis()+".png";
				Picture picture = new Picture();
				picture.setCamId(element.getId());
				picture.setPath(saveTo);
				pictureDao.save(picture);
				saveImage(element.getUrl(), 
						saveTo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	
}
