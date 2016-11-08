package model;

import java.util.ArrayList;
import java.util.HashMap;
import org.bson.types.ObjectId;

public class PublicDiscussion extends Discussion{
	
	
	  private ArrayList<String> viewedBy=new ArrayList<String>();
        private ArrayList<Comments> comment=new ArrayList<Comments>(); 
        private ArrayList<String> category=new ArrayList<String>();
	private Boolean spam;
        private ArrayList<HashMap<String,String>> spamHistory=new ArrayList<HashMap<String,String>>();
public Boolean isSpam()
	{
	return this.spam;
	}
	public void setPost(String userId,String topic,String content,String category)
	{
	
        super.setUserId(userId);
        super.setTopic(topic);
        String[] str=content.split("#");
        super.setContent(str[0]);
        String str1="";
        	for(int i=1;i<str.length;i++)
                {
                    str1=str1 + str[i]+",";
                }
                str1=str1.substring(0,str1.length()-1);
                super.setTags(str1);
                
        String[] temp=category.split(",");
        	for(int i=0;i<temp.length;i++)
                {
                        this.category.add(temp[i]);
                }
	}
 public String getCategoryString()
	{
            String str="";
	for(int i=0;i<category.size();i++)
        {
           str=str+category.get(i)+",";
        }
        str=str.substring(0, str.length()-1);
        return str;
	}
               public void setCategory(String tagString)
        {
            String[] str=tagString.split(",");
        
        	for(int i=0;i<str.length;i++)
                {
                    category.add(str[i]);
                }
        }

        
}
