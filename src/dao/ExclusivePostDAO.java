package dao;

/**
 *
 * @author Herat Patel
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import model.ExclusiveDiscussion;
import org.bson.types.ObjectId;
import service.ExclusivePostConverter;

public class ExclusivePostDAO {
    private DBCollection col;
    
    public ExclusivePostDAO(MongoClient mongo){
        this.col = mongo.getDB("vidico").getCollection("exclusive");
    }
    
    public ExclusiveDiscussion newPost(ExclusiveDiscussion ed){
        DBObject doc = ExclusivePostConverter.toDBObject(ed);
        this.col.insert(doc);
        ed.setid((ObjectId)doc.get("_id"));
        return ed;
    }
    
    public ArrayList<ExclusiveDiscussion> showPost(){
        ArrayList<ExclusiveDiscussion> ared = new ArrayList<ExclusiveDiscussion>();
        
        try{
            BasicDBObject whereQuery = new BasicDBObject();
            DBObject oneDetails;
            
            DBCursor cursor = this.col.find();
            
            while(cursor.hasNext()) {
                oneDetails=cursor.next();
                ExclusiveDiscussion ed;
                ed=ExclusivePostConverter.toExclussiveDiscussion(oneDetails);
                ared.add(ed);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"dao");
        }
        return ared;
    }
    
    public ExclusiveDiscussion showDetailPost(String postid){
        ExclusiveDiscussion ed = null;
        
        try{
            BasicDBObject whereQuery = new BasicDBObject();
            DBObject oneDetails;
            
            whereQuery.put("_id", new ObjectId(postid));
            DBCursor cursor = this.col.find(whereQuery);
            oneDetails=cursor.next();
            ed = ExclusivePostConverter.toExclussiveDiscussion(oneDetails);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"dao");
        }
        return ed;
    }
}
