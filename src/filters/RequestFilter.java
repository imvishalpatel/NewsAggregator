package filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestFilter implements Filter {

    public RequestFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Enumeration<String> parameters=request.getParameterNames();
		
		while(parameters.hasMoreElements()){
			String name=parameters.nextElement();
			request.setAttribute(name, request.getParameter(name));
		}
		
		System.out.println("Request Filter");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}