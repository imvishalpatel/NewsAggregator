
package controllers;

import com.mongodb.MongoClient;
import dao.ExclusivePostDAO;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ExclusiveDiscussion;

/**
 *
 * @author Herat Patel
 */
public class ActionAddExclusivePost implements Action{
    private String viewPage = "";
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userId="";
        String topic=request.getParameter("topic");
        String content=request.getParameter("content");
        String[] category = request.getParameterValues("cb");
        String discussionDate = request.getParameter("disDate");
        String discussionTime = request.getParameter("disTime");
        String NoOfGuestUsers = request.getParameter("guestCount");
        int noGusers = Integer.parseInt(NoOfGuestUsers);
        request.setAttribute("NoOfGuestUsers",noGusers);
        LinkedList<String> errors = new LinkedList<String>();
        
        try{
            if(topic==null || topic.equals("")){
                errors.add("Topic Should be Selected");
            }
            if(content== null || content.equals("")) {
                errors.add("Content cannot be blank");
            }
            if(discussionDate==null || discussionDate.equals("")){
                errors.add("Discussion Date Should be Selected");
            }
            if(discussionTime==null || discussionTime.equals("")){
                errors.add("Discussion Time should be Selected");
            }
            if(NoOfGuestUsers==null || NoOfGuestUsers.equals("")){
                errors.add("Number Of Guest Users Should be Selected");
            }
            if (errors.size() > 0) {
                request.setAttribute("error", errors);
                viewPage = "ExclusivePost.jsp";
            }
            else {
                ExclusiveDiscussion ed = new ExclusiveDiscussion(userId, topic, content, category);
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
                ExclusivePostDAO exclusivePostDao = new ExclusivePostDAO(mongo);
                exclusivePostDao.newPost(ed);
            } 
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"Action");
        }
        
        return viewPage;
    }
}
