/*
 * Created on 22.11.2004
 */
package servlets;

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
	
		String action = request.getParameter("action");
		
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
				
		Long id = null;
		
		if (request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
				
		if(action.equals("add")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camAdd.jsp");
			dispatcher.forward(request, response);		
		} else if(action.equals("edit")) {			
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
				camDao.toggleStatus(id);
				response.sendRedirect(request.getContextPath() + "/list");
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
				
		Cam cam = new Cam();		
		cam.setId(id);
		cam.setName(name);
		cam.setUrl(url);
		
		
		try {		
			camDao.save(cam);
			response.sendRedirect(request.getContextPath() + "/list");
		}  catch (CamNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
