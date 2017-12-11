/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author tiago
 */
public class Endereco {
    private String cidade;
    private String bairro;
    private String cep;
    private String rua;
    private String numero;
    private String infoAdcional;
    
    public Endereco(){
        
    }
    
    public Endereco(
        String cidade,
        String bairro,
        String cep,
        String rua,
        String numero,
        String infoAdcional
    ){
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.infoAdcional = infoAdcional;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInfoAdcional() {
        return infoAdcional;
    }

    public void setInfoAdcional(String infoAdcional) {
        this.infoAdcional = infoAdcional;
    }
       
    @Override
    public String toString() {
        return "Usuario{" +                
                ", cidade:'" + cidade + '\'' +
                ", bairro:'" + bairro + '\'' +
                ", cep:" + cep + '\'' +
                ", rua:" + rua + '\'' +
                ", numero:" + numero + '\''+
                ", infoAdcional:" + infoAdcional +
                '}';
    }
    
}
