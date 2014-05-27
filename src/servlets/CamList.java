package servlets;

import helper.SessionHelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cam;
import dao.CamDao;
import dao.CamDaoImpl;
import dao.DaoFactory;

public class CamList extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final CamDao camDao = DaoFactory.getInstance().getCamDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		List<Cam> collection = camDao.list();
		request.setAttribute("cams", collection);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camList.jsp");
		dispatcher.forward(request, response);		
	}
}
