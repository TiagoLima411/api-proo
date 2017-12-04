/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import org.bson.types.ObjectId;

/**
 *
 * @author tiago
 */
public class Usuario {   
    private ObjectId id;
    private String nomeCompleto;
    private String senha;
    private String email;
    private String fone1;
    private String fone2;
    private Endereco endereco;

    public Usuario (){
        
    }
    
    public Usuario(        
        String nomeCompleto,
        String senha,
        String email,   
        String fone1,
        String fone2,
        Endereco endereco){
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.fone1 = fone1;
        this.fone2 = fone2;
        this.endereco = endereco;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
        
}
