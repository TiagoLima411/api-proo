/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author tiago
 */

import static com.mongodb.client.model.Filters.eq;
import dao.UsuarioDao;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import vo.Usuario;

public class AuthenticationService {

    public boolean authenticate(String authCredentials) {

        if (null == authCredentials) {
            return false;
        }
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String cpfAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            cpfAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                cpfAndPassword, ":");
        final String cpf = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.listarPorCpfEmail(cpf, password);
                
        // we have fixed the userid and password as admin
        // call some UserService/LDAP here
        
        boolean authenticationStatus = usuario.getCpf().equals(cpf)
                && usuario.getSenha().equals(password);
        return authenticationStatus;
    }
}
