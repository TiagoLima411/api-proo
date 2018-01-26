/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.MongoConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import vo.Usuario;
import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author tiago
 */
public class UsuarioDao implements IDao{
    
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    MongoCollection<Usuario> collection = MongoConnection.getInstance().getDB()
                    .getCollection("Usuarios", Usuario.class)
                    .withCodecRegistry(pojoCodecRegistry);                                    
            
    public void salvar(Usuario usuario) {                    
        collection.insertOne(usuario);
        System.out.println("Salvo:>"+usuario);
    }    
    
    public void salvarLista(List usuario){        
        collection.insertMany(usuario);                
    }    
    
    public List<Usuario> listar() {
        
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();                        
        for (Usuario usuario : collection.find()) {
            listaUsuarios.add(usuario);
            System.out.println("List:>"+usuario);
        }        
        return listaUsuarios;
    }    
    
    public Usuario listarPorCpf(String cpf){
        Usuario usuario = null;
        usuario = collection.find(eq("cpf", cpf)).first();                            
        return usuario;
    }
        
    public Usuario authenticationPorCpfESenha(String cpf, String senha){
        Usuario usuario = collection.find(and (eq("cpf", cpf),eq("senha", senha))).first();        
        return usuario;
    }
        
    public void atualizarPorId(String id, String email, String nomeCompleto){        
        collection.updateOne(eq("_id",new ObjectId(id)), 
                combine(set("email", email), 
                        set("nomeCompleto", nomeCompleto)));                
    }
    
    public void atualizarPorCpf(String cpf, Usuario usuario){
        try{            
            collection.replaceOne(eq("cpf", cpf), usuario);            
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario "+ex.getMessage());
        }
    }
        
    public void removerPorCpf(String cpf){
        try{
            collection.deleteOne(eq("cpf", cpf));
        }catch(Exception ex){
            System.out.println("Não foi possível deletar usuario"+ex.getMessage());
        }
    }
        
    public void removerPorAtributo(String atributo, String valor){        
        try{
           collection.deleteMany(eq(atributo, valor));
        }catch(Exception ex){
            System.out.println("Atributo inválido"+ex.getMessage());
        }                
    }                        
}
