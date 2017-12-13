/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesMetodosDao;

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
    /*
    @Test
    public void deveInserirUsuarioNoBanco(){
        Usuario usuario = new Usuario(
            "Tiago de Lima Alves",
            "056",
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
        usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }
    
    @Test
    public void deveSalvarUmaListaDeUsuarios(){
        List<Usuario> usuario = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuario.add(new Usuario("Tiago de Lima Alves","045","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.salvarLista(usuario);
        usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }
         
    @Test
    public void deveListarTodosOsUsuarios(){
        List<Usuario> usuarioInserir = new ArrayList<Usuario>();
        for(int i=0; i<100; i++){
            usuarioInserir.add(new Usuario("Tiago de Lima Alves","000","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007","")));
        }
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> listUsuario = new ArrayList<Usuario>();
        listUsuario = usuarioDao.listar();
        
        for (Usuario usuario : listUsuario) {
            System.out.println("Buca todos:> "+ usuario
                    
            );
        }
        usuarioDao.removerPorAtributo("nomeCompleto", "Tiago de Lima Alves");
    }
    
    @Test
    public void deveEncontrarUmUsuarioPassandoIdComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario();
            usuario = usuarioDao.listarPorCpf("00066677700");
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
            Usuario usuario = new Usuario("Tiago de Lima Alves","000","123455","hotmail.com","uuuu9999","88888888",new Endereco("Maceio","Tabuleirio","57084040","A 40","007",""));
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.atualizarPorId("5a2e73816394faaaa3cc1502", "hotmail.com", "Tiago de Lima Alves");
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario"+ex.getMessage());
        }        
    }
    
    @Test
    public void deveRemoverUmUsuarioPassandoIdComoParametro(){
        try{
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.removerPorCpf("00099988866");
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
    }*/
}
