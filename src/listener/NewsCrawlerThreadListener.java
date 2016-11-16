/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import crawling.NewsCrawler;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author vishal
 */
@WebListener
public class NewsCrawlerThreadListener implements ServletContextListener {

    private final long threadPoolTime = 1;
    private ScheduledExecutorService scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if ((scheduler == null) || (!scheduler.isTerminated())) {
            ServletContext ctx = sce.getServletContext();
            String newsThreadValue = ctx.getInitParameter("NewsThread");
            if (newsThreadValue.equalsIgnoreCase("TRUE")) {
                scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.scheduleAtFixedRate(new NewsCrawler(sce), 0, threadPoolTime, TimeUnit.MINUTES);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("Scheduler Shutting down successfully " + new Date());
            scheduler.shutdown();
        } catch (Exception ex) {
            System.out.println("ERROR IN NEWS THREAD ->" + ex.getMessage());
        }
    }
}
