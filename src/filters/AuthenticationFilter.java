package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Authentication Filter");
		Object username=((HttpServletRequest)request).getSession().getAttribute("username");
		Object action=((HttpServletRequest)request).getAttribute("action");
		
		if(username==null){
			
			System.out.println("username is null");
			if(action!=null)
			{
				System.out.println("Action not null:"+action.toString());
				if(action.toString().equals("signup") || action.toString().equals("adduser") || 
					action.toString().equals("confirm") || action.toString().equals("viewlogin") || 
					action.toString().equals("login")){
					System.out.println("action one amoingst the main");
				}
				else{
					((HttpServletRequest)request).setAttribute("action","viewlogin");
				}
			}
			
			else{
				System.out.println("converting action");
				((HttpServletRequest)request).setAttribute("action","viewlogin");
				((HttpServletRequest)request).setAttribute("action","viewlogin");
			}
		}
		
		if(action==null){
			((HttpServletRequest)request).setAttribute("action","viewlogin");
		}
		
		System.out.println(((HttpServletRequest)request).getAttribute("action"));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
