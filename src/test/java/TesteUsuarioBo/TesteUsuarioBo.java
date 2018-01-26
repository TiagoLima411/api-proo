/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteUsuarioBo;

import bo.UsuarioBo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import junit.framework.TestCase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.Test;
import util.MongoConnection;
import vo.Endereco;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class TesteUsuarioBo extends TestCase {    
    UsuarioBo usuarioBo;
    MongoCollection<Usuario> collection;        
    UsuarioMock usuarioMock;            
    @Override    
    protected void setUp() throws Exception{
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        collection = MongoConnection.getInstance().getDB()
                .getCollection("Usuarios", Usuario.class)
                .withCodecRegistry(pojoCodecRegistry);
        
        usuarioBo = new UsuarioBo();
        usuarioMock = new UsuarioMock();
        super.setUp();
    }
    
    @Test
    public void testDeveriaPermitirSalvarCpfComOnzeDigitos() throws Exception {
        String message = null;
        try{
            message = usuarioBo.salvarUsuario(usuarioMock.usuario1);
            System.out.println("message: "+message);
        }catch(Exception ex){            
            assertFalse(ex.getMessage(),false);
        }                         
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarCpfQueJaExiste() throws Exception {
        String message = null;
        
        try{
            collection.insertOne(usuarioMock.usuario1);            
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario1);
            System.out.println("message: "+message);
        } catch (Exception ex) {               
            assertFalse(ex.getMessage(), false);
        } finally{
            collection.deleteOne(eq("cpf", usuarioMock.usuario1.getCpf()));
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarCpfComMenosDeOnzeDigitos() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario2);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarCpfComMaisDeOnzeDigitos() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario3);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarCpfNUllo() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario4);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarCpfComStringVazia() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario5);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
        
    @Test
    public void testNaoDeveriaPermitirSalvarNomeComStringVazia() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario6);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarNomeComMenosDeTresCaracteres() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario7);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarNomeComMaisDeSetentaCaracteres() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario8);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
    
    @Test
    public void testNaoDeveriaPermitirSalvarNomeNulo() throws Exception {
        String message = null;
        try {
            message = usuarioBo.salvarUsuario(usuarioMock.usuario9);
            System.out.println("message: "+message);
        } catch (Exception ex) {            
            assertFalse(ex.getMessage(), false);
        }
    }
}
