package listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import trend.TrendUpdatingThread;

@WebListener
public class TrendContextListener implements ServletContextListener{

	ScheduledExecutorService scheduler;
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		// TODO Auto-generated method stub
	
		if(scheduler==null || !scheduler.isTerminated()){
			scheduler=Executors.newSingleThreadScheduledExecutor();
			scheduler.scheduleAtFixedRate(new TrendUpdatingThread(contextEvent.getServletContext()), 0, 1, TimeUnit.MINUTES);
		}
	}	
}
