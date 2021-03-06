/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ProdutoDao;
import dao.UsuarioDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vo.Produto;

import vo.Usuario;

/**
 *
 * @author tiago
 */
public class ProdutoBo {

    
    private float calculaValorTotal(float qtd, float precoUnidade){
        float precoTotal = precoUnidade * qtd;
        return precoTotal;
    }
    
    private float calculaValorDoICMS(float porcentagemICMS, float qtd, float precoUnidade){
        float valorTotal = calculaValorTotal(qtd, precoUnidade);
        float valorICMS = valorTotal * (porcentagemICMS/100); 
        return valorICMS;
    }        
    
    private float calculaValorDoIPI(float porcentagemIPI, float qtd, float precoUnidade){
        float valorTotal = calculaValorTotal(qtd, precoUnidade);
        float valorIPI = valorTotal * (porcentagemIPI/100); 
        return valorIPI;
    }
    
    private boolean validaDescricao(String descricaoProd) {        
        if(descricaoProd.isEmpty() || descricaoProd.length()< 8 || descricaoProd.length() > 70 || descricaoProd == null || descricaoProd == ""){            
            return false;
        }else{
            return true;
        }
    }
            
    public String salvarProduto(Produto produto) throws Exception{
        
        ProdutoDao produtoDao = new ProdutoDao();                            
        
        if (!validaDescricao(produto.getDescricao()))
            return "Descrição do produto inválida (descrição deve conter entre 8 e 70 caracteres)";                
                       
        float valorTotalProduto = calculaValorTotal(produto.getQtd(), produto.getVunt());
        produto.setVtot(valorTotalProduto);
                
        float valorDoICMS = calculaValorDoICMS(produto.getAicm(), produto.getQtd(), produto.getVunt());
        produto.setVicm(valorDoICMS);
        
        float valorDoIPI = calculaValorDoIPI(produto.getAipi(), produto.getQtd(), produto.getVunt());
        produto.setVipi(valorDoIPI);
       
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat cod = new SimpleDateFormat("HHmmssMM/dd/yyyy");
        Date  dataHora = new Date();
        String reportDate = df.format(dataHora);   
        String codProd = cod.format(dataHora);   
        
        produto.setBicm(valorTotalProduto);
        produto.setBipi(valorTotalProduto);
        produto.setDataHoraAtual(reportDate);
        produto.setCodProd(codProd);
        
        try {            
            produtoDao.salvar(produto);
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar produto: " + ex);
        }
        return "Produto Cadastrado com sucesso";
    }
    
    public List<Produto> listarProdutos() throws Exception {        
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> listaProdutos = new ArrayList<Produto>();
        try{
            listaProdutos = produtoDao.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }        
        return listaProdutos;
    }
    
//    public Usuario listarUsuarioPorCpf(String cpf) throws Exception {
//        
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario usuario = new Usuario();
//        
//        if (!validaCpf(cpf)) {
//            return usuario = null;
//        }
//        
//        try {
//            usuario = usuarioDao.listarPorCpf(cpf);
//            System.out.println("user: "+usuario.getNomeCompleto());
//        } catch (Exception ex) {
//            return usuario = null;
//        }
//        
//        return usuario;
//    }
    /*
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
    }*/
}
