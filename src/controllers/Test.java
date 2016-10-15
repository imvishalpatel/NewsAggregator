package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "Testjsp.jsp";
	}

}
