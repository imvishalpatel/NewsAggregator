package controllers;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static String ACTION_MAPPING = "controllers/ActionMapping.properties";

    public Controller() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Object theAction = request.getAttribute("action");
        String actionName = "test";

        if (theAction != null) {
            System.out.print("I cam here also..");
            actionName = theAction.toString();
        }

        Action action = getActionFromConfig(actionName);

        String view = action.process(request, response);

        System.out.print("I cam here..");
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private Action getActionFromConfig(String actionName)
            throws IOException {

        Properties map = new Properties();
        map.load(this.getClass().getClassLoader().getResourceAsStream(ACTION_MAPPING));

        String actionClass = map.getProperty(actionName.toLowerCase());
        Action action = null;

        try {
            action = (Action) Class.forName(actionClass).newInstance();
        } catch (Exception e) {
            action = new Error(e);
        }

        return action;
    }
}
