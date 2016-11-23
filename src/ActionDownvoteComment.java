package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.PublicDiscussion;

import com.mongodb.MongoClient;

import dao.PublicPostDAO;
import java.util.ArrayList;
import model.Comment;

public class ActionDownvoteComment implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) {
		
		MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
		PublicPostDAO dao=new PublicPostDAO(mongo);
		
		String username=request.getAttribute("username").toString();
		String pid=request.getAttribute("pid").toString();
		String cid=request.getAttribute("cid").toString();
		
		PublicDiscussion pd=dao.getPublicDiscussion(pid);
		
                ArrayList<Comment> comments=pd.getComments();
                
                Comment ref=null;
                for(Comment comment : comments){
                    if(comment.getId().equals(cid)){
                        Integer i=comment.getVoteList().get(username);
                        ref=comment;
                        if(i==null){
                            comment.votes(-1, username);
                        }
		
                        else{
                            if(i==1){
				comment.votes(0, username);
                            }
                            else{
				comment.votes(-1, username);
                            }
                        }
                    }
                }
		
		
		JSONObject json=null;
		try {
			json=new JSONObject("{'status':'success', 'upvote':'"+ref.getUpVotes()+"', 'downvote':'"+ref.getDownVotes()+"'}");
			response.getWriter().write(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("vote:"+pd.getVotes());
		//request.setAttribute("json", json);
		dao.updatePublicDiscussion(pd);
		return "JSON";
	}
	
}
