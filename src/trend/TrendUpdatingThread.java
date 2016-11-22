package trend;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import model.PublicDiscussion;

import com.mongodb.MongoClient;

public class TrendUpdatingThread implements Runnable{

	private MongoClient mongo;
	private ServletContext context;
	
	public TrendUpdatingThread(ServletContext context) {
		this.context=context;
		this.mongo=(MongoClient) context.getAttribute("MONGO_CLIENT");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Trend trend=new Trend();
		ArrayList<PublicDiscussion> list=trend.trending(mongo);
		context.setAttribute("trending", list);
	}

}
