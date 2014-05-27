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

import dao.GroupDao;
import dao.DaoFactory;
import exception.GroupNotFoundException;
import exception.GroupNotSavedException;
import model.Group;

public class GroupEdit extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final GroupDao groupDao = DaoFactory.getInstance().getGroupDao();

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
				
		if(action.equals("groupEdit")){
			
			Group group = new Group();
			groupDao.listUserInGroup(id);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/groupEdit.jsp");
			dispatcher.forward(request, response);		
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
			
	}
}
