/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesMetodosDao;

import static com.mongodb.client.model.Filters.eq;
import dao.IDao;
import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import vo.Endereco;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class TesteUsuarioDao{
    
    @Test
    public void deveInserirUsuarioNoBanco(){
        Usuario usuario = new Usuario(
            "Tiago de Lima Alves",
            "123456",
            "email@hotmail.com",   
            "12341234",
            "43214312",
            new Endereco(
                "Maceio",
                "B Bentes",
                "57084-040",
                "A 40",
                "44",
                ""
            )
        );
        
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.salvar(usuario);
        //usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }
    
    @Test
    public void deveSalvarUmaListaDeUsuarios(){
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.salvarLista(usuario);
        usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }
         
    @Test
    public void deveListarTodosOsUsuarios(){
        List<Usuario> usuarioInserir = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuarioInserir.add(new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> listUsuario = new ArrayList<Usuario>();
        listUsuario = usuarioDao.listar();
        
        for (Usuario usuario : listUsuario) {
            System.out.println("Buca todos:>"+
                    usuario.getNomeCompleto()+
                    usuario.getEmail()+
                    usuario.getFone1()
            );
        }
        usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }

    @Test
    public void deveEncontrarUmUsuarioPassandoIdComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario();
            usuario = usuarioDao.listarPorId("5a255b126394faaaa39009a4");
        }catch(Exception ex){
            System.out.println("Não foi possível localizar Usuario"+ex.getMessage());
        }
    }

    @Test//Refatorar futuramente 
    public void deveAtualizarUmUsuarioPassandoIdNomeEmailComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.atualizarPorId("5a255b126394faaaa39009a4", "emailteste@hotail", "TEste de atualização");
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario"+ex.getMessage());
        }        
    }
    
    @Test//Refatorar futuramente 
    public void deveAtualizarUmUsuarioPassandoIdEObjeto(){
        try{
            Usuario usuario = new Usuario("Tiago de Lima Alves","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007",""));
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.atualizarPorId("5a255b126394faaaa39009a4",usuario);
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario"+ex.getMessage());
        }        
    }
    
    @Test
    public void deveRemoverUmUsuarioPassandoIdComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.removerPorId("5a255b126394faaaa39009a4");
        }catch(Exception ex){
            System.out.println("Não foi possível remover usuario"+ex.getMessage());
        }                
    }
    
    @Test
    public void deveRemoverVariosUsuariosQueTenhamAChaveEOValorPassandoComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
        }catch(Exception ex){
            System.out.println("Não foi possível remover usuario"+ex.getMessage());
        }
    }
}
