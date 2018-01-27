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
    private int ncm;
    private int cst;
    private int cfop;
    private String un;
    private int qtd;
    private float vUnitario;
    private float vTotal;
    private float bCalcIcms;
    private float bCalcIpi;
    private float vIcms;
    private float vIpi;
    private float aliqIcms;
    private float aliqIpi;

    public Produto(String codProd, String descricao, int ncm, int cst, int cfop, String un, int qtd, float vUnitario, float aliqIcms, float aliqIpi) {
        this.codProd = codProd;
        this.descricao = descricao;
        this.ncm = ncm;
        this.cst = cst;
        this.cfop = cfop;
        this.un = un;
        this.qtd = qtd;
        this.vUnitario = vUnitario;                
        this.aliqIcms = aliqIcms;
        this.aliqIpi = aliqIpi;
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

    public int getNcm() {
        return ncm;
    }

    public void setNcm(int ncm) {
        this.ncm = ncm;
    }

    public int getCst() {
        return cst;
    }

    public void setCst(int cst) {
        this.cst = cst;
    }

    public int getCfop() {
        return cfop;
    }

    public void setCfop(int cfop) {
        this.cfop = cfop;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public float getvUnitario() {
        return vUnitario;
    }

    public void setvUnitario(float vUnitario) {
        this.vUnitario = vUnitario;
    }

    public float getvTotal() {
        return vTotal;
    }

    public void setvTotal(float vTotal) {
        this.vTotal = vTotal;
    }

    public float getbCalcIcms() {
        return bCalcIcms;
    }

    public void setbCalcIcms(float bCalcIcms) {
        this.bCalcIcms = bCalcIcms;
    }

    public float getvIcms() {
        return vIcms;
    }

    public void setvIcms(float vIcms) {
        this.vIcms = vIcms;
    }

    public float getAliqIcms() {
        return aliqIcms;
    }

    public void setAliqIcms(float aliqIcms) {
        this.aliqIcms = aliqIcms;
    }

    public float getbCalcIpi() {
        return bCalcIpi;
    }

    public void setbCalcIpi(float bCalcIpi) {
        this.bCalcIpi = bCalcIpi;
    }

    public float getvIpi() {
        return vIpi;
    }

    public void setvIpi(float vIpi) {
        this.vIpi = vIpi;
    }

    public float getAliqIpi() {
        return aliqIpi;
    }

    public void setAliqIpi(float aliqIpi) {
        this.aliqIpi = aliqIpi;
    }
               
}
