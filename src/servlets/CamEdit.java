/*
 * Created on 22.11.2004
 */
package servlets;

import helper.SessionHelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CamDao;
import dao.DaoFactory;
import exception.CamNotFoundException;
import exception.CamNotSavedException;
import exception.CamNotToggledException;
import model.Cam;

public class CamEdit extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final CamDao camDao = DaoFactory.getInstance().getCamDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		if(!SessionHelper.currentUser(request).getGroup().equals("admin")){
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/noaccess.jsp");
			requestDispatcher.forward(request, response);
			 return;
		}
		if(!SessionHelper.currentUser(request).getGroup().equals("admin")){
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/noaccess.jsp");
			requestDispatcher.forward(request, response);
			 return;
		}
		
		String action = request.getParameter("action");
		
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
				
		Long id = null;
		
		if (request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
				
		if(action.equals("camAdd")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camAdd.jsp");
			dispatcher.forward(request, response);		
		} else if(action.equals("camEdit")) {			
			try {
				Cam cam = camDao.getCam(id);
				request.setAttribute("cam", cam);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camEdit.jsp");
				dispatcher.forward(request, response);
			} catch (CamNotFoundException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}				
		} else if(action.equals("toggleStatus")) {			
			try {
				String status = request.getParameter("status");			
				camDao.toggleStatus(id, status);
				response.sendRedirect(request.getContextPath() + "/camList");
			} catch (CamNotToggledException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		Long id = null;
		
		if(request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
		
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String status = request.getParameter("status");
				
		Cam cam = new Cam();		
		cam.setId(id);
		cam.setName(name);
		cam.setUrl(url);
				
		if(status == null){
			cam.setStatus(false);
		}else{
			cam.setStatus(true);
		}

		try {		
			camDao.save(cam);
			response.sendRedirect(request.getContextPath() + "/camList");
		}  catch (CamNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
