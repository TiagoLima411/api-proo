/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteUsuarioBo;

import org.junit.Test;
import vo.Endereco;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class UsuarioMock {
                
    Usuario usuario1 = new Usuario(
                "Usuario Teste",
                "12345678910",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );

        Usuario usuario2 = new Usuario(
                "Usuario Teste",
                "123456789",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );
        
        Usuario usuario3 = new Usuario(
                "Usuario Teste",                
                "1234567890123",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );
        
        Usuario usuario4 = new Usuario(
                "Usuario Teste",
                null,
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );
        
        Usuario usuario5 = new Usuario(
                "Usuario Teste",
                "",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );
        
        Usuario usuario6 = new Usuario(
                "",
                "12345678901",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );

Usuario usuario7 = new Usuario(
                "Us",
                "12345678901",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );        

Usuario usuario8 = new Usuario(
                "UsuarioTestUsuarioTestUsuarioTestUsuarioTestUsuarioTestUsuarioTestUsuarioTest",
                "12345678901",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );        

Usuario usuario9 = new Usuario(
                null,
                "12345678901",
                "123456",
                "email@hotmail.com",
                "12341234",
                "43214312",
                false,
                new Endereco(
                        "Maceio",
                        "B Bentes",
                        "57084-040",
                        "A 40",
                        "44",
                        ""
                )
        );    
}
