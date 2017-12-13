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
    
    public void salvarLista(List usuario);                        
    
    public List<Usuario> listar();
    
    public Usuario listarPorCpf(String cpf);
    
    public Usuario authenticationPorCpfEmail(String cpf, String senha);        
    
    public void atualizarPorId(String id, String email, String nomeCompleto);
    
    public void atualizarPorCpf(String cpf, Usuario usuario);
        
    public void removerPorCpf(String cpf);
        
    public void removerPorAtributo(String atributo, String valor);
}
