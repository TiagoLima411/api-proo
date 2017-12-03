/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeCollections;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import util.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.Test;
import vo.Endereco;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class TesteQueries {    
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@ds241895.mlab.com:41895/proo");
            MongoClient mongoClient = new MongoClient(connectionString);
            
            MongoDatabase database = mongoClient.getDatabase("proo");
            
    MongoCollection<Usuario> collection = database
                    .getCollection("test", Usuario.class)
                    .withCodecRegistry(pojoCodecRegistry);
    
    @Test
    public void deveSalvarUsuarioNoBanco() {
        Endereco en = new Endereco("Maceio","Tabuleirio","57084040","A 40","007","");
        Usuario us = new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",en);
            
        collection.insertOne(us);
               
    }
    
}
