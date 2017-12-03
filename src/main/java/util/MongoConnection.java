/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author tiago
 */
public class MongoConnection {
    private static MongoConnection uniqInstance;
    private static int mongoInstance = 1;
    
    private MongoClient mongo;
    private MongoDatabase db;
    private String host = "@ds241895.mlab.com:41895";
    private String user = "root";
    private String password = "root";
    private String dataBase = "proo";
    
    public MongoConnection() {
        
    }        
    
    //garante sempre uma unica instancia
    public static synchronized MongoConnection getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MongoConnection();
        }
        return uniqInstance;
    }
    
    //garante um unico objeto mongo
    public MongoDatabase getDB() {
        if (mongo == null) {
            MongoClientURI uri = new MongoClientURI("mongodb://"+user+":"+password+host+"/"+dataBase);
            mongo = new MongoClient(uri);
            db = mongo.getDatabase("proo");
            System.out.println("Mongo instance equals :> " + mongoInstance++);
        }
        return db;
    }
}
