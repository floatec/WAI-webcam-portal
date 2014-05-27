package servlets;

import helper.SessionHelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Group;
import dao.GroupDao;
import dao.GroupDaoImpl;
import dao.DaoFactory;

public class GroupList extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	final GroupDao groupDao = DaoFactory.getInstance().getGroupDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		List<Group> collection = groupDao.list();
		request.setAttribute("groups", collection);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/groupList.jsp");
		dispatcher.forward(request, response);		
	}
}
