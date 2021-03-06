package servlets;

import helper.SessionHelper;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cam;
import model.Picture;

import dao.CamDao;
import dao.CamDaoImpl;
import dao.DaoFactory;
import dao.PictureDao;

@WebServlet("/picture")
public class PictureList extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final CamDao camDao = DaoFactory.getInstance().getCamDao();
	final PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		
		//all cams for current user
		List<Cam> collectioncam = camDao.getCamsForuser(SessionHelper.currentUser(request).getId());
//		for (int i = 0; i < collectioncam.size(); i++) {
//			System.out.println( collectioncam.get(i));
//			
//		}
		if (collectioncam.size()==0){
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/noaccess.jsp");
			requestDispatcher.forward(request, response);
			 return;
		 }
		long l = collectioncam.get(0).getId();
		if(request.getSession().getAttribute("cam-cam")!=null){
			l =(long) request.getSession().getAttribute("cam-cam");
		}
		if (request.getParameter("cam")!=null){
		 l = Long.parseLong(request.getParameter("cam").toString());
		 try {
			 if (!collectioncam.contains(camDao.getCam(l))){
				 RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/jsp/noaccess.jsp");
					requestDispatcher.forward(request, response);
				 return;
			 }
		} catch (Exception e) {
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/noaccess.jsp");
			requestDispatcher.forward(request, response);
		 return;
		}
		
		}
			
		String date= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(request.getSession().getAttribute("cam-date")!=null){
			date =(String) request.getSession().getAttribute("cam-date");
		}
		if(request.getParameter("date")!=null){
		date = request.getParameter("date").toString();
		}
		Cam cam = camDao.getCam(l);
		
		// current cam
		request.setAttribute("cam", cam);
		
		// pictures for current cam
		List<Picture> collection = pictureDao.listByCam(l, date);
		request.setAttribute("pictures", collection);
		
		request.setAttribute("cams", collectioncam);
		request.setAttribute("date",date);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/pictureList.jsp");
		dispatcher.forward(request, response);	
		request.getSession().setAttribute("cam-date", date);
		request.getSession().setAttribute("cam-cam", cam.getId());
	}
}
