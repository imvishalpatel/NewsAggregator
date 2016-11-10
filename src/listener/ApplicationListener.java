package listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.User;

public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		// TODO Auto-generated method stub
		
		Map<String, User> activeUsers=((Map<String, User>)arg.getServletContext().getAttribute("activeUsers"));
		activeUsers=null;
	}

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		// TODO Auto-generated method stub
		Map<String, User> activeUsers=new HashMap<>();
		arg.getServletContext().setAttribute("activeUsers", activeUsers);
	}

}
