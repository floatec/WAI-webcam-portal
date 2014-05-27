package helper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class SessionHelper {
	public static boolean checklogin(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("user")!=null){
			return true;
		}else{
			try {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/jsp/Login.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return false;
		}
	}
	public static User currentUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
}
