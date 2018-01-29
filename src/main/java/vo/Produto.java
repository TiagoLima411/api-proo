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
public class Produto {

    private String codProd;
    private String descricao;
    private String un;
    private CalculoImpostos calculoImpostos;     

    public Produto(String codProd, String descricao, String un, CalculoImpostos calculoImpostos) {
        this.codProd = codProd;
        this.descricao = descricao;
        this.un = un;
        this.calculoImpostos = calculoImpostos;
    }   

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public CalculoImpostos getCalculoImpostos() {
        return calculoImpostos;
    }

    public void setCalculoImpostos(CalculoImpostos calculoImpostos) {
        this.calculoImpostos = calculoImpostos;
    }
    
    
}
