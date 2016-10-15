/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

/**
 *
 * @author vishal
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient mongo = (MongoClient) sce.getServletContext()
                .getAttribute("MONGO_CLIENT");
        mongo.close();
        System.out.println("MongoClient closed successfully");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext ctx = sce.getServletContext();

            MongoClientURI uri = null;
            uri = new MongoClientURI("mongodb://vishal:vishal@ds057066.mlab.com:57066/vidico",MongoClientOptions.builder().cursorFinalizerEnabled(false));
            MongoClient mongo = new MongoClient(uri);

//            MongoClient mongo = new MongoClient(
//                    ctx.getInitParameter("MONGODB_HOST"),
//                    Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
            System.out.println("MongoClient initialized successfully");
            sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
        } catch (Exception e) {
            throw new RuntimeException("MongoClient init failed " + e.getMessage());
        }
    }

}
