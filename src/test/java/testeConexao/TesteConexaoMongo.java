/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeConexao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author tiago
 */
public class TesteConexaoMongo extends TestCase{

    private MongoClient mongo;
    private MongoDatabase db;                 

    public MongoDatabase getDB(String host, String user, String password, String dataBase) {
        if (mongo == null) {
            MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + password + host + "/" + dataBase);
            mongo = new MongoClient(uri);
            db = mongo.getDatabase("proo");
        }
        return db;
    }

    @Test
    public void testDeveriaConectarDadosInformadosCorretamente() throws Exception {
        MongoDatabase mongoDatabase = getDB("@ds241895.mlab.com:41895", "root", "root", "proo");
        assertNotNull(mongoDatabase);
        System.out.println("Database Mongo "+mongoDatabase);
    }

    @Test
    public void testNaoDeveriaConectarSeDadosInformadosErrados() throws Exception {
        MongoDatabase mongoDatabase = null;
        try {
            mongoDatabase = getDB("@ds241895.mlab.com:41895", "user", "password", "proo");
        } catch (Exception e) {
            assertNull(mongoDatabase);
        }        
    }
}
