/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.UsuarioDao;
import vo.Endereco;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class UsuarioBo {

    public String validarUsuario(Usuario usuario) {

        if (usuario.getNomeCompleto().isEmpty() || usuario.getCpf().isEmpty()) {
            return "Nome e/ou CPF inválido";
        } else if (usuario.getNomeCompleto().length() < 3
                || usuario.getNomeCompleto().length() > 70
                || usuario.getCpf().length() < 11
                || usuario.getCpf().length() > 11) {
            return "Nome e/ou CPF inválido";
        } else if (usuario.getNomeCompleto() == null || usuario.getCpf() == null) {
            return "Nome e/ou CPF não informado";
        } else if (usuario.getNomeCompleto() == "" || usuario.getCpf() == "") {
            return "Nome e/ou CPF não informado";
        }

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            usuarioDao.salvar(usuario);
            return "Usuario Cadastrado com sucesso";
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar usuario: " + ex);
        }
        return "";
    }

}
