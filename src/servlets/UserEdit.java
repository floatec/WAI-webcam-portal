package servlets;

import helper.SessionHelper;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cam;
import model.CamToUser;
import model.User;
import dao.CamDao;
import dao.DaoFactory;
import dao.UserDao;
import exception.CamNotFoundException;
import exception.CamNotSavedException;
import exception.CamNotToggledException;

public class UserEdit extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	final UserDao userDao = DaoFactory.getInstance().getUserDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionHelper.checklogin(request, response)){
			return;
		}
		if(!SessionHelper.currentUser(request).getGroup().equals("admin") && !SessionHelper.currentUser(request).getGroup().equals("schichtleiter") ){
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
		
		if(id == 1){
			User currentUser = SessionHelper.currentUser(request);
			if(SessionHelper.currentUser(request).getId() != 1){
				response.sendRedirect(request.getContextPath() + "/userList");
				return;
			}
		}
		
		if(action.equals("userAdd")){
			List<CamToUser> camList = userDao.getUserCams(id);
			request.setAttribute("cams", camList);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userAdd.jsp");
			dispatcher.forward(request, response);		
		} else if(action.equals("userEdit")) {			
			try {
				User user = userDao.getUser(id);
				CamDao camDao = DaoFactory.getInstance().getCamDao();
				List<CamToUser> camList = userDao.getUserCams(id);
				List<CamToUser> camListForUser = new ArrayList<CamToUser>();
			
				// Ist der Benutzer kein Admin, darf der Benutzer nicht die Berechtigung für alle Cams sondern nur für seine vergeben
				if(!SessionHelper.currentUser(request).getGroup().equals("admin")){
						
					List<Cam> collectioncam = camDao.getCamsForuser(SessionHelper.currentUser(request).getId());
					
					// Wenn die Liste des Benutzer leer ist, darf er keine Cams zum Nutzer hinzufügen
					if(collectioncam.isEmpty()){
						for (int i = 0; i < camList.size(); i++) {
							camList.removeAll(camList);
						}
					}else{
						// Alle Cams 
						for (int i = 0; i < camList.size(); i++) {
							// AlleCams vom User
							for (int j = 0; j < collectioncam.size(); j++) {
								// Wenn die IDS der camms gleich sind, darf der User die Cam sehen und anderen hinzufügen
								if(camList.get(i).getCamid() == collectioncam.get(j).getId()){								
									CamToUser ctu = new CamToUser();
									ctu.setCamid(camList.get(i).getCamid());
									ctu.setAccess(camList.get(i).getAccess());
									ctu.setName(camList.get(i).getName());
									camListForUser.add(ctu);
								}
							}
						}
					}		
				}else{
					camListForUser =  userDao.getUserCams(id);
				}
				
				request.setAttribute("user", user);
				request.setAttribute("cams", camListForUser);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userEdit.jsp");
				dispatcher.forward(request, response);
					
			} catch (CamNotFoundException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}				
		} else if(action.equals("userDelete")) {			
			try {
				if(id != 1){
					userDao.deleteUser(id);					
				}
				response.sendRedirect(request.getContextPath() + "/userList");
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
		
		String[] camsToUser = request.getParameterValues("cams"); 
				
		try {		
			userDao.save(user, camsToUser);
			response.sendRedirect(request.getContextPath() + "/userList");
		}  catch (CamNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
