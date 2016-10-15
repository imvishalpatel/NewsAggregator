package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error implements Action {

    private Exception exception;
    private String message = "Some problem has occured.";

    public Error(Exception e) {
        exception = e;
    }

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("adminError", exception.toString());
        request.setAttribute("userError", message);
        return "error.jsp";
    }

}
