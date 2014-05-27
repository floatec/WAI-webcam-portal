package servlets;

import java.io.IOException;

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
		long l = Long.parseLong(request.getParameter("cam").toString());
		Cam cam = camDao.getCam(l);
		request.setAttribute("cam", cam);
		
		List<Picture> collection = pictureDao.listByCam(l, request.getParameter("date").toString());
		request.setAttribute("pictures", collection);
		List<Cam> collectioncam = camDao.list();
		for (int i = 0; i < collectioncam.size(); i++) {
			System.out.println( collectioncam.get(i));
			
		}
		System.out.println(collectioncam.getClass().getName());
		request.setAttribute("cams", collectioncam);
		request.setAttribute("date", request.getParameter("date").toString());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/pictureList.jsp");
		dispatcher.forward(request, response);		
	}
}
