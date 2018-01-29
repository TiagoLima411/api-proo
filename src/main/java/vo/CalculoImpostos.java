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
public class CalculoImpostos {
    private float ncm;
    private float cst;
    private float cfop;
    private float qtd;
    private float vunt;
    private float vtot;
    private float bicm;
    private float bipi;
    private float vipi;
    private float aicm;
    private float aipi;

    public CalculoImpostos(float ncm, float cst, float cfop, float qtd, float vunt, float vtot, float bicm, float bipi, float vipi, float aicm, float aipi) {
        this.ncm = ncm;
        this.cst = cst;
        this.cfop = cfop;
        this.qtd = qtd;
        this.vunt = vunt;
        this.vtot = vtot;
        this.bicm = bicm;
        this.bipi = bipi;
        this.vipi = vipi;
        this.aicm = aicm;
        this.aipi = aipi;
    }

    public float getNcm() {
        return ncm;
    }

    public void setNcm(float ncm) {
        this.ncm = ncm;
    }

    public float getCst() {
        return cst;
    }

    public void setCst(float cst) {
        this.cst = cst;
    }

    public float getCfop() {
        return cfop;
    }

    public void setCfop(float cfop) {
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

    public float getBicm() {
        return bicm;
    }

    public void setBicm(float bicm) {
        this.bicm = bicm;
    }

    public float getBipi() {
        return bipi;
    }

    public void setBipi(float bipi) {
        this.bipi = bipi;
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

}
