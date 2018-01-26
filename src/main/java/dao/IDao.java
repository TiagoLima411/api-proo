/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public interface IDao {
    
    public void salvar(Usuario usuario);        
    
    public List<Usuario> listar();
    
    public Usuario listarPorCpf(String cpf);                
    
    public void atualizarPorCpf(String cpf, Usuario usuario);
        
    public void removerPorCpf(String cpf);
            
}
