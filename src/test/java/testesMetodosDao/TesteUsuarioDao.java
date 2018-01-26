/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesMetodosDao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import dao.IDao;
import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.List;
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
public class TesteUsuarioDao extends TestCase{
    UsuarioDao usuarioDAO;
    Usuario usuario1;
    Usuario usuario2;
    MongoCollection<Usuario> collection; 
    
    @Override
    protected void setUp() throws Exception {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        collection = MongoConnection.getInstance().getDB()
                .getCollection("Usuarios", Usuario.class)
                .withCodecRegistry(pojoCodecRegistry);

        usuario1 = new Usuario(
                "Usuario Teste",
                "000",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );
        
        usuario2 = new Usuario(
                "Usuario Teste de Atualização",
                "000",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );        
        
        usuarioDAO = new UsuarioDao();
        super.setUp();
    }

    @Test
    public void testDeveriaInserirUsuarioNoBanco() throws Exception{
        try {
            usuarioDAO.salvar(usuario1);
        } catch (Exception e) {

            assertFalse(e.getMessage(), true);

            throw new Exception(e.getMessage());
        } finally {
            collection.deleteOne(eq("cpf", "000"));
        }
    }

    @Test
    public void testDeveriaAlterarUsuarioCadastrado() throws Exception {
        
        try {
            collection.insertOne(usuario1);
            usuarioDAO.atualizarPorCpf("000", usuario2);
        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
            throw new Exception(e.getMessage());
        } finally {
            collection.deleteOne(eq("cpf", "000"));
        }
    }
    
    @Test
    public void testDeveriaExcluirUsuarioCadastradoValido() throws Exception {
        try {
            collection.insertOne(usuario1);
            usuarioDAO.removerPorCpf("000");
        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
            throw new Exception(e.getMessage());
        } finally {
            //rollback();
        }
    }

    
    @Test
    public void testDeveriaRetornarOsDadosDosUsuariosCadastrados() throws Exception {
        try {
            List<Usuario> listaUsuarios;

            listaUsuarios = usuarioDAO.listar();

            for (Usuario usuario : listaUsuarios) {
                System.out.println(usuario.getNomeCompleto()+
                        "\n"+usuario.getCpf()+
                        "\n"+usuario.getEmail()+
                        "\n"+usuario.getFone1()+
                        "\n");
            }

        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
        } finally {
            //rollback();
        }
    }
    
    @Test
    public void testDeveriaRetornarOsDadosDoUsuarioPeloCpf() throws Exception {
        try {
            collection.insertOne(usuario1);
            Usuario usuario = usuarioDAO.listarPorCpf("000");

            System.out.println(usuario.getNomeCompleto()
                    + "\n" + usuario.getCpf()
                    + "\n" + usuario.getEmail()
                    + "\n" + usuario.getFone1()
                    + "\n");

        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
        } finally {
            collection.deleteOne(eq("cpf", "000"));
        }
    }
    
    @Test
    public void testDeveriaRemoverUsuarioPeloCpf() throws Exception {
        try {
            collection.insertOne(usuario1);
            usuarioDAO.removerPorCpf("000");

        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
        } finally {
            collection.deleteOne(eq("cpf", "000"));
        }
    }    
}
