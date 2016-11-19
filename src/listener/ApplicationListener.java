package listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.User;

@WebListener
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		// TODO Auto-generated method stub
		
		Map<String, User> activeUsers=((Map<String, User>)arg.getServletContext().getAttribute("activeUsers"));
		activeUsers=null;
                arg.getServletContext().setAttribute("activeUsers",activeUsers);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		// TODO Auto-generated method stub
		Map<String, User> activeUsers=new HashMap<>();
		arg.getServletContext().setAttribute("activeUsers", activeUsers);
	}

}
