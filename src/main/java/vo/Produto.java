/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author tiago
 */
public class Produto {
    private ObjectId _id;
    private String codProd;
    private String descricao;
    private String un;       
    private String ncm;
    private String cst;
    private String cfop;
    private float qtd;
    private float vunt;
    private float vtot;
    private float vicm;
    private float bipi;
    private float bicm;
    private float vipi;
    private float aicm;
    private float aipi;
    Date dataHoraAtual;

    public Produto() {
    }    
    
    public Produto(String codProd ,String descricao, String un, String ncm, String cst, String cfop, float qtd, float vunt, float aicm, float aipi) {
        
        this.descricao = descricao;
        this.un = un;
        this.ncm = ncm;
        this.cst = cst;
        this.cfop = cfop;
        this.qtd = qtd;
        this.vunt = vunt;                    
        this.aicm = aicm;
        this.aipi = aipi;
    }    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
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

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public float getQtd() {
        return qtd;
    }

    public void setQtd(float qtd) {
        this.qtd = qtd;
    }

    public float getVunt() {
        return vunt;
    }

    public void setVunt(float vunt) {
        this.vunt = vunt;
    }

    public float getVtot() {
        return vtot;
    }

    public void setVtot(float vtot) {
        this.vtot = vtot;
    }

    public float getVicm() {
        return vicm;
    }

    public void setVicm(float vicm) {
        this.vicm = vicm;
    }

    public float getBipi() {
        return bipi;
    }

    public void setBipi(float bipi) {
        this.bipi = bipi;
    }

    public float getBicm() {
        return bicm;
    }

    public void setBicm(float bicm) {
        this.bicm = bicm;
    }

    public float getVipi() {
        return vipi;
    }

    public void setVipi(float vipi) {
        this.vipi = vipi;
    }

    public float getAicm() {
        return aicm;
    }

    public void setAicm(float aicm) {
        this.aicm = aicm;
    }

    public float getAipi() {
        return aipi;
    }

    public void setAipi(float aipi) {
        this.aipi = aipi;
    }

    public Date getDataHoraAtual() {
        return dataHoraAtual;
    }

    public void setDataHoraAtual(Date dataHoraAtual) {
        this.dataHoraAtual = dataHoraAtual;
    }

}
