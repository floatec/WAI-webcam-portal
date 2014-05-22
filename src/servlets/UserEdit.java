package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dao.DaoFactory;
import dao.UserDao;
import exception.CamNotFoundException;
import exception.CamNotSavedException;
import exception.CamNotToggledException;

public class UserEdit extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	final UserDao userDao = DaoFactory.getInstance().getUserDao();

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
				
		if(action.equals("userAdd")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userAdd.jsp");
			dispatcher.forward(request, response);		
		} else if(action.equals("userEdit")) {			
			try {
				User user = userDao.getUser(id);
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userEdit.jsp");
				dispatcher.forward(request, response);
			} catch (CamNotFoundException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}				
		} else if(action.equals("deleteUser")) {			
			try {
				String status = request.getParameter("status");			
				userDao.deleteUser(id);
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();		
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);

				
		try {		
			userDao.save(user);
			response.sendRedirect(request.getContextPath() + "/userList");
		}  catch (CamNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
