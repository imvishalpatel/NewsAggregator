/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 *
 * @author vishal
 */
public class ConfirmationDAO {

    private DBCollection col;

    public ConfirmationDAO(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("confirmation");
    }
    
    public void addData(String username,String email,String key){
        
    }
}
