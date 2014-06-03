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
import model.User;
import dao.CamDao;
import dao.CamDaoImpl;
import dao.DaoFactory;
import dao.UserDao;

public class UserList extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		if(!SessionHelper.currentUser(request).getGroup().equals("admin") && !SessionHelper.currentUser(request).getGroup().equals("schichtleiter") ){
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/noaccess.jsp");
			requestDispatcher.forward(request, response);
			 return;
		}
		User ses = SessionHelper.currentUser(request);
		List<User> collection = userDao.list();
		User loggedInUser = SessionHelper.currentUser(request);	
		request.setAttribute("loggedInUser", loggedInUser);
		request.setAttribute("users", collection);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userList.jsp");
		dispatcher.forward(request, response);
	}
}
