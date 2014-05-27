/*
 * Created on 22.11.2004
 */
package servlets;

import helper.SessionHelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import dao.DaoFactory;
import exception.CamNotSavedException;
import exception.GroupNotFoundException;
import exception.GroupNotSavedException;
import model.CamToUser;
import model.Group;
import model.UserInGroup;

public class GroupEdit extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final GroupDao groupDao = DaoFactory.getInstance().getGroupDao();

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
			List<UserInGroup> groupList = groupDao.listUserInGroup(id);
			request.setAttribute("groups", groupList);
			request.setAttribute("group", id);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/groupEdit.jsp");
			dispatcher.forward(request, response);		
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		Long id = null;
		
		if(request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
		
		String[] usersInGroup = request.getParameterValues("users"); 
			
		try {		
			groupDao.saveUsersToGroup(id, usersInGroup);
			response.sendRedirect(request.getContextPath() + "/groupList");
		}  catch (CamNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
