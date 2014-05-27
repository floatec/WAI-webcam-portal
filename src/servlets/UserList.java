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
		List<User> collection = userDao.list();
		request.setAttribute("users", collection);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userList.jsp");
		dispatcher.forward(request, response);
	}
}
