/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.sequence.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import util.sequence.model.SequenceId;

/**
 *
 * @author vishal
 */
public class SequenceDAO {

    private DBCollection col;

    public SequenceDAO(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("sequence");
    }

    public long getNextSequenceId(String key) throws Exception {
        if (key == null) {
            throw new Exception("Key is empty");
        }
        SequenceId seq = new SequenceId();
        DBObject query = new BasicDBObject();
        query.put("_id", key);
        DBObject obj = col.findOne(query);

        if (obj == null) {
            seq.setId(key);
            seq.setSeq(1);
        } else {
            seq.setId(key);
            seq.setSeq((long) obj.get("seq") + 1);
        }
        save(seq);
        return seq.getSeq();
    }

    public void save(SequenceId model) throws Exception {
        if (model == null) {
            throw new Exception("Model is null or empty");
        }

        DBObject query = new BasicDBObject();
        query.put("_id", model.getId());
        query.put("seq", model.getSeq());
        DBObject searchQuery = new BasicDBObject().append("_id", model.getId());
        col.save(query);
    }
}
