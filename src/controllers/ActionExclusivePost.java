
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Herat Patel
 */
public class ActionExclusivePost implements Action {
    private String viewPage = "";
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
         return viewPage;
    }
}
