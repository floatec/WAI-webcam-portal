package servlets;

import helper.SessionHelper;

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

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {

	final UserDao userDao = DaoFactory.getInstance().getUserDao();

	public void doPostGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		try {
			User curUser= userDao.getUser(loginName);
			if (curUser.isPasswordEqual(password) ||request.getSession().getAttribute("user")!=null ) {
				request.getSession().setAttribute("user", curUser);
				response.setStatus( 302 );
				response.sendRedirect( "pictureList");
			} else {
				out.println("Das Passwort ist falsch. Bitte versuche es erneut<br />");
				out.println("<a href='Login.jsp'>Hier</a> geht es zur�ck zur Eingabemaske!");
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/jsp/Login.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			out.println("Der Bentzername ist falsch. Bitte versuche es erneut<br />");
			out.println("<a href='Login.jsp'>Hier</a> geht es zur�ck zur Eingabemaske!");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/Login.jsp");
			requestDispatcher.forward(request, response);
		}
		

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