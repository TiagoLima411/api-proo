/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeCollections;

import static org.junit.Assert.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
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
            .getCollection("Usuarios", Usuario.class)
            .withCodecRegistry(pojoCodecRegistry);

    private boolean inserido;

    @Test
    public void deveInserirUsuarioNoBanco() {
        Endereco en = new Endereco("Maceio", "Tabuleirio", "57084040", "A 40", "007", "");
        Usuario us = new Usuario("Tiago de Lima Alves", "05437260460", "123455", "hotmail.com", "uuuu9999", "88888888", en);

        try {
            inserido = true;
            collection.insertOne(us);
        } catch (Exception ex) {
            inserido = false;
            System.out.println("Error: " + ex);
        }

        assertNotNull("Usuario Inserido", us);
        assertTrue(inserido);

        collection.deleteOne(eq("nomeCompleto", "Tiago de Lima Alves"));
    }

    @Test
    public void deveInserirVariosUsuariosnoBanco() {
        List<Usuario> usuario = new ArrayList<Usuario>();
        for (int i = 0; i < 100; i++) {
            usuario.add(new Usuario("Tiago de Lima Alves", "045", "123455", "hotmail.com", "uuuu9999", "88888888", new Endereco("Maceio", "Tabuleirio", "57084040", "A 40", "007", "")));
        }
        try {
            collection.insertMany(usuario);
            assertNotNull("Inserido com sucesso", usuario);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        collection.deleteMany(eq("nomeCompleto", "Tiago de Lima Alves"));
    }

    @Test
    public void deveImprimirNoConsoleTodosOsUsuariosDaCollection() {
        List<Usuario> usuarioInserido = new ArrayList<Usuario>();
        for (int i = 0; i < 100; i++) {
            usuarioInserido.add(new Usuario("Tiago de Lima Alves", "05437260458", "123455", "hotmail.com", "uuuu9999", "88888888", new Endereco("Maceio", "Tabuleirio", "57084040", "A 40", "007", "")));
        }
        try {
            collection.insertMany(usuarioInserido);
            for (Usuario usuario : collection.find()) {
                System.out.println("Buca todos:>"
                        + usuario.getId()
                        + usuario.getNomeCompleto()
                        + usuario.getCpf()
                        + usuario.getEmail()
                        + usuario.getFone1()
                );
                assertNotNull("Sucesso: ", usuario);
            }
        } catch (Exception ex) {
            System.out.println("Erro :" + ex);
        }
        collection.deleteMany(eq("nomeCompleto", "Tiago de Lima Alves"));
    }
    
    @Test
    public void deveImprimirNoConsoleUmUsuarioComChaveEValorEspecificoFiltroEQ() {
        try {

            Endereco en = new Endereco("Maceio", "Tabuleirio", "57084040", "A 40", "007", "");
            Usuario us = new Usuario("Tiago de Lima Alves", "054", "123456", "hotmail.com", "uuuu9999", "88888888", en);

            collection.insertOne(us);
            Usuario usuarioBuscar = collection.find(and(eq("nomeCompleto", "Tiago de Lima Alves"), eq("senha", "123456"))).first();

            assertNotNull("Erro: ", usuarioBuscar);

            System.out.println("Usuario:> " + usuarioBuscar.getNomeCompleto() + " " + usuarioBuscar.getEmail());

            collection.deleteOne(eq("nomeCompleto", "Tiago de Lima Alves"));

        } catch (Exception ex) {
            System.out.println("Usuario não localizado: " + ex.getMessage());
        }
    }
    
    @Test
    public void deveAtualizarUsuarioPorId() {
        Endereco en = new Endereco("Maceio", "Tabuleirio", "57084040", "A 40", "007", "");
        Usuario us = new Usuario("Tiago de Lima Alves", "054", "123456", "hotmail.com", "uuuu9999", "88888888", en);

        collection.insertOne(us);

        try {

            collection.updateOne(eq("nomeCompleto", "Tiago de Lima Alves"),
                    combine(set("email", "tiago_lima411@hotmail.com"),
                            set("nomeCompleto", "Tiago Lima Foda Alves")));

            Usuario usuarioBuscar = collection.find(and(eq("nomeCompleto", "Tiago Lima Foda Alves"),
                    eq("email", "tiago_lima411@hotmail.com")))
                    .first();
            
            assertNotNull("Erro: ", usuarioBuscar);
            System.out.println("Usuario: " + usuarioBuscar);
            
        } catch (Exception ex) {
            System.out.println("Não foi possível atualizar o Usuario Id não encontrado" + ex.getMessage());

        }
    }
    
    
    @Test
    public void deveAtualizarMultiplosUsuarios(){
        
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","065","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        
        UpdateResult updateResult = collection.updateMany((eq( "email", "hotmail.com")), set("email", null));        
        System.out.println("Atualizando Multiplos documentos "+updateResult.getModifiedCount());
        
        assertNotNull("Erro: ", updateResult);
        
        collection.deleteMany(eq("email", null));
    }
        
    @Test 
    public void deveAtualizarUsuarioEmEspecificoPorCPF(){
        try{
            Endereco en = new Endereco("Rio Largo","Tabuleirio do Pinto","57084040","A 40","007","");
            Usuario us = new Usuario("Camilla dos Santos","04554676543","123455","hotmail.com","uuuu9999","88888888",en);
            collection.insertOne(us);                        
            
            UpdateResult updateResult = collection.updateOne((eq( "email", "hotmail.com")), set("email", null));
            
            assertNotNull("Erro: ", updateResult);
            collection.deleteOne(eq("cpf", "04554676543"));
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario "+ex.getMessage());
        }
    }
    
    @Test
    public void deveDeletarDocumentoEmEspecíficoPorId(){
        try{
            Endereco en = new Endereco("Rio Largo","Tabuleirio do Pinto","57084040","A 40","007","");
            Usuario us = new Usuario("Camilla dos Santos","04554676543","123455","hotmail.com","uuuu9999","88888888",en);
            collection.insertOne(us);                                    
            
            DeleteResult deleteResult = collection.deleteOne(eq("cpf", "04554676543"));
                                    
            System.out.println("Delete result"+deleteResult);
            assertNotNull("Sucesso: ", deleteResult);            
        }catch(Exception ex){
            System.out.println("Não foi possível deletar usuario");
        }
    }
    /*
    @Test
    public void deveDeletarTodosOsUsuariosQueOFiltroEncontrar(){
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","065","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        
        DeleteResult deleteResult = collection.deleteMany(eq("email", "hotmail.com"));
        System.out.println("Registros Removidos:>"+deleteResult.getDeletedCount());
    }
*/    
}
