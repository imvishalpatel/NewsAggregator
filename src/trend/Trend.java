package trend;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.PublicDiscussion;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

import dao.PublicPostDAO;

public class Trend {
	
	public static void main(String[] args) {
		System.out.println("om");
		/*
		try {
            MongoClientURI uri = null;
            uri = new MongoClientURI("mongodb://vishal:vishal@ds057066.mlab.com:57066/vidico",MongoClientOptions.builder().cursorFinalizerEnabled(false));
            MongoClient mongo = new MongoClient(uri);

            Trend trend=new Trend();
            ArrayList<PublicDiscussion> s= trend.trending(mongo);
            
          System.out.println("The topics are");
            if(s==null){
            	System.out.println("Empty");
            }
            else
            	for(int i=0 ; i<s.size() ; i++)
            		System.out.println(s.get(i).getTopic());
            
            //System.out.println("Changing date..");
            
            //PublicPostDAO dao=new PublicPostDAO(mongo);
            
            //PublicDiscussion pb=new PublicDiscussion("imsunil", "Testing the input", "This is just a sample input");
            //dao.newPost(pb);
            //PublicDiscussion pd=dao.getPublicDiscussion("58341beac04223176058f236");
            //pd.setDate(new Date());
            //dao.updatePublicDiscussion(pd);
            
            
        }
        
        catch (Exception e) {
        	System.out.println("Here is an error:\n"+e.toString());
            throw new RuntimeException("MongoClient init failed " + e.getMessage());
        }*/
		
		String[] id={"1", "2", "3", "4"};
        
        double[][] mat={ 	{500, 50, 80, 200},
        					{200, 200, 0, 0},
        					{300, 100, 100, 100},
        					{400, 100, 0, 0}
        			   };
        Trend trend=new Trend();
        id=trend.getMostTrending(mat, id);
        System.out.println(id[0] + " " + id[1] + " " + id[2] + " " + id[3]);
	}
	
	public ArrayList<PublicDiscussion> trending(MongoClient mongo){
		HashMap<String, PublicDiscussion> list=null;
		
		//MongoClient mongo = (MongoClient) arg.getServletContext().getAttribute("MONGO_CLIENT");
		PublicPostDAO dao=new PublicPostDAO(mongo);
		
		list=dao.getAllPublicPosts();
		
		String[] pid=null;
		double[] views=null;
		double[] time=null;
		double[] comments=null;
		double[] votes=null;
		
		if(list!=null){
			views=new double[list.size()];
			time=new double[list.size()];
			pid=new String[list.size()];
			comments=new double[list.size()];
			votes=new double[list.size()];
			
			DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date refDate=new java.util.Date();
		
			try {
				refDate=format.parse("01/05/2016");
			} catch (ParseException e) {
				// TODO Auto-generated catch block	
			}
			
			int j=0;
			
			//Populate Data
			for(String key : list.keySet()){
				
				PublicDiscussion pd=list.get(key);
				pid[j]=pd.getid().toString();
				views[j]=pd.getViewCount();
				time[j]=pd.getDate().getTime()-refDate.getTime();
				votes[j]=pd.getUpVotes()+pd.getDownVotes();
				comments[j]=pd.getComments().size();
				//System.out.println(pd.getTopic()+ "\t" + time[j] + "\t" + votes[j] + "\t" + comments[j] + "\t" + views[j] + "\t" + pd.getDate());
				j++;
			}
		
			/*views=zscore(views);
			time=zscore(time);
			votes=zscore(votes);
			comments=zscore(comments);*/
			
			double matrix[][]=new double[list.size()][4];
		
			for(int i=0 ; i<list.size() ; i++){
				matrix[i][0]=time[i];
				matrix[i][1]=views[i];
				matrix[i][2]=comments[i];
				matrix[i][3]=votes[i];
			}
		
			String[] trends=null;
			trends=getMostTrending(matrix, pid);
		
			//System.out.println(trends[0]);
		
			ArrayList<PublicDiscussion> topics=new ArrayList<PublicDiscussion>();
		
			for(String ids : trends){
				topics.add(list.get(ids));
			}
			
			return topics;

		}
		
		return null;
	}
	
	public String[] getMostTrending(double[][] matrix, String[] pids){
		/* 
		 * Input Matrix
		 * 				pid, date, view,comments,votes
		 * 				      0.4	0.10   0.2   0.3
		*/
		
		int size=pids.length<5?pids.length:5;
		String[] trends=new String[size];
		double[] ans=new double[matrix.length];
		double[] w={0.4, 0.1, 0.2, 0.3};
		
		System.out.println("Assigning...");
		for(int i=0 ; i<matrix.length ; i++){
			ans[i]=0;
		}
		
		System.out.println("Computing...");
		for(int i=0 ; i<matrix.length ; i++){
			System.out.println("For "+i);
			for(int j=0 ; j<4 ; j++){
				//ans[i]+=matrix[i][j]>0?matrix[i][j]*w[j]:0;
				//System.out.println( "For " + j + ": " + matrix[i][j] + " " + ans[i]);
				ans[i]+=matrix[i][j]*w[j];
			}
			//System.out.println(pids[i] + "\t" + ans[i]);
		}
	
		//System.out.println("Before sorting");
		for(int i=0 ; i<matrix.length ; i++){
			//System.out.println(pids[i] + "\t" + ans[i]);
		}
		
		double temp;
		String stemp;
		for(int i=0 ; i<matrix.length-1 ; i++){
			for(int j=i+1 ; j<matrix.length ; j++){
				if(ans[i]<=ans[j]){
					temp=ans[i];
					ans[i]=ans[j];
					ans[j]=temp;
					
					stemp=pids[i];
					pids[i]=pids[j];
					pids[j]=stemp;
				}
			}
		}
		
		//System.out.println("After sorting");
		for(int i=0 ; i<matrix.length ; i++){
			//System.out.println(pids[i] + "\t" + ans[i]);
		}
		
		for(int i=0 ; i<5 && i<ans.length ; i++){
			trends[i]=pids[i];
		}
		
		return trends;
	}
		
	double[] zscore(double[] list){
		double[] ans=new double[list.length];
		
		double m=mean(list);
		double d=sd(list, m);
		
		for(int i=0 ; i<list.length ; i++){
			ans[i]=0;
			if((int)d==0)
				ans[i]=(list[i]-m)/d;
			
			System.out.println("Mean:" + m + " SD:" + d + "ans:" + ans[i]);
		}
		
		return ans;
	}
	
	double mean(double[] list){
		double ans=0;
		
		for(int i=0 ; i<list.length ; i++){
			ans+=list[i];
		}
		
		return ans;
	}
	
	double sd(double[] list, double m){
		double ans=0;
		
		for(int i=0 ; i<list.length ; i++){
			ans+=Math.pow(list[i]-m, 2);
		}
		
		return Math.sqrt(ans/(list.length-1));
	}
}