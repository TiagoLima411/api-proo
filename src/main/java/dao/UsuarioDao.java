/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.DBCursor;
import util.MongoConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

/*
DBObject
BasicDBObject
É através deles que a API transforma os parametros Map em um documento JSON.


os metodos de CRUD da API são acessives através de um objeto 
DBCollection. Estes metodos recebem como parametros os objetos 
DBObject ou BasicDBObject.
*/

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import vo.Endereco;
import vo.Usuario;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
//java.util.Map ----> Fizemos assim em conseqüência de trabalharmos com chave:valor
/**
 *
 * @author tiago
 */
public class UsuarioDao{                
    
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    MongoCollection<Usuario> collection = MongoConnection.getInstance().getDB()
                    .getCollection("Usuarios", Usuario.class)
                    .withCodecRegistry(pojoCodecRegistry);
        
    
    protected MongoCollection getDbCollection() {
        return collection;
    }
            
    public void salvar(Usuario usuario) {        
            
        collection.insertOne(usuario);
        System.out.println("Salvo:>"+usuario);
        
    }    
    
    public void salvarListaDeUsuarios(List usuario){                        
        collection.insertMany(usuario);        
    }    
    
    public List listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();        
        
        for (Usuario usuario : collection.find()) {
            listaUsuarios.add(usuario);
        }
        
        return listaUsuarios;
    }    
    
    public Usuario listarUsuarioPorId(String id){                                                       
        
        Usuario usuario = collection.find(eq("_id",new ObjectId(id))).first();            
                            
        return usuario;
    }
    
    //Fazer sobrecarga de metodos
    public void atualizarUsuarioPorId(String id, String email, String nomeCompleto){        
        collection.updateOne(eq("_id",new ObjectId(id)), 
                combine(set("email", email), 
                        set("nomeCompleto", nomeCompleto)));                
    }
    
    public void atualizarUsuarioPorId(String id, Usuario usuario){
        try{            
            collection.replaceOne(eq("_id", new ObjectId(id)), usuario);
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario "+ex.getMessage());
        }
    }
        
    public void removerUsuarioPorId(String id){
        try{
            collection.deleteOne(eq("_id", new ObjectId(id)));
        }catch(Exception ex){
            System.out.println("Não foi possível deletar usuario"+ex.getMessage());
        }
    }
        
    public void removerUsuarioPorAtributo(String atributo, String valor){        
        try{
           collection.deleteMany(eq(atributo, valor));
        }catch(Exception ex){
            System.out.println("Atributo inválido"+ex.getMessage());
        }                
    }
    /*
    @Test
    public void deveAtualizarMultiplosUsuarios(){
        
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        
        UpdateResult updateResult = collection.updateMany((eq( "email", "hotmail.com")), set("email", null));        
        System.out.println("Atualizando Multiplos documentos "+updateResult.getModifiedCount());
        
        collection.deleteMany(eq("email", null));
    }*/
        
    
    
    
}
