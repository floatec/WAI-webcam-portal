package servlets;


import grabber.PictureGrabber;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class test
 */
@WebServlet("/grabber")
public class PictureGrabberServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final int INTERVAL = 60;   
    
    private final ScheduledExecutorService scheduler =
    	       Executors.newScheduledThreadPool(1);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PictureGrabberServer() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    
     	
    	scheduler.scheduleAtFixedRate(new PictureGrabber(), 0, INTERVAL, TimeUnit.SECONDS);
          
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

}
