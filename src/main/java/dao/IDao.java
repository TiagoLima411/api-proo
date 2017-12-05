/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public interface IDao {
    
    public void salvar(Usuario usuario);
    
    public void salvarLista(List usuario);
    
    public List listar();
    
    public Usuario listarPorId(String id);    
    
    public void atualizarPorId(String id, String email, String nomeCompleto);
    
    public void atualizarPorId(String id, Usuario usuario);
        
    public void removerPorId(String id);
        
    public void removerPorAtributo(String atributo, String valor);
}
