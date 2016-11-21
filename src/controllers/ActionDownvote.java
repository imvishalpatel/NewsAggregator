package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.PublicDiscussion;

import com.mongodb.MongoClient;

import dao.PublicPostDAO;

public class ActionDownvote implements Action{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) {
		
		MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
		PublicPostDAO dao=new PublicPostDAO(mongo);
		
		System.out.println("Finally!!!");
		String username=request.getAttribute("username").toString();
		String pid=request.getAttribute("pid").toString();
		
		PublicDiscussion pd=dao.getPublicDiscussion(pid);
		
		Integer i=pd.getVoteList().get(username);
		
		if(i==null){
			pd.vote(username, -1);
		}
		
		else{
			if(i==1){
				pd.vote(username, 0);
			}
			else{
				pd.vote(username, -1);
			}
		}
		
		JSONObject json=null;
		try {
			json=new JSONObject("{'status':'success', 'vote':'"+pd.getUpVotes()+"'}");
			response.getWriter().write(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("vote:"+pd.getVotes());
		
		dao.updatePublicDiscussion(pd);
		
		return "JSON";
	}
	
}
