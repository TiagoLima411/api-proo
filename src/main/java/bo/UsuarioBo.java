/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.List;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class UsuarioBo {
    
    private boolean validaUsuario(String cpf){
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();
        try{
            usuario = usuarioDao.listarPorCpf(cpf);            
        }catch(Exception ex){            
            return false;            
        }
        return true;   
    }
    
    private boolean validaNome(String nome) {        
        if(nome.isEmpty() || nome.length()<3 || nome.length() > 70 || nome == null || nome == ""){            
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validaCpf(String cpf){                
        
        if(cpf.isEmpty() || cpf.length()<11 || cpf.length() > 11 || cpf == null || cpf == ""){
            return false;
        }else{
            return true;
        }
    }
    
    public String salvarUsuario(Usuario usuario) throws Exception{
        
        UsuarioDao usuarioDao = new UsuarioDao();
        
        if (!validaNome(usuario.getNomeCompleto()) 
                || !validaCpf(usuario.getCpf())
                || validaUsuario(usuario.getCpf())){
            return "Nome e/ou CPF inválido e/ou CPF já existe";
        }
        
        try {            
            usuarioDao.salvar(usuario);
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar usuario: " + ex);
        }
        return "Usuario Cadastrado com sucesso";
    }
    
    public List<Usuario> listarUsuarios() throws Exception {        
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios = usuarioDao.listar();
        return listaUsuarios;
    }
    
    public Usuario listarUsuarioPorCpf(String cpf) throws Exception {
        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = new Usuario();
        
        if (!validaCpf(cpf)) {
            return usuario = null;
        }
        
        try {
            usuario = usuarioDao.listarPorCpf(cpf);
            System.out.println("user: "+usuario.getNomeCompleto());
        } catch (Exception ex) {
            return usuario = null;
        }
        
        return usuario;
    }
    
    public String atualizarUsuarioPorCpf(String cpf, Usuario usuario) throws Exception {
        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuarioDB;

        if (!validaCpf(cpf)) {
            return "Cpf inválido";
        }

        try {
            usuarioDB = usuarioDao.listarPorCpf(cpf);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return "Cpf Inexistente";
        }

        try {
            if (usuarioDB != null) {
                usuarioDao.atualizarPorCpf(cpf, usuario);
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return ("Não foi possível atualizar usuario!");
        }

        return "Usuario Atualizado com sucesso";
    }
    
    public String removeUsuarioPorCpf(String cpf) throws Exception{
        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuarioDB;

        if (!validaCpf(cpf)) {
            return "Cpf inválido";
        }

        try {
            usuarioDB = usuarioDao.listarPorCpf(cpf);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return "Cpf Inexistente";
        }

        try {
            if (usuarioDB != null) {
                usuarioDao.removerPorCpf(cpf);
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return ("Não foi possível remover usuario!");
        }

        return "Usuario Removido com sucesso";
    }
}