package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import dao.CamDao;
import dao.DaoFactory;
import dao.UserDao;

@WebServlet("/logout")
public class Logout extends HttpServlet {

	final UserDao userDao = DaoFactory.getInstance().getUserDao();

	public void doPostGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		request.getSession().setAttribute("user", null);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/Login.jsp");
			requestDispatcher.forward(request, response);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)

	throws IOException, ServletException {
		doPostGet(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPostGet(request, response);

	}
}