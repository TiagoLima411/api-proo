/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.google.gson.Gson;
import dao.UsuarioDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vo.Usuario;
/**
 *
 * @author tiago
 */
@Path("/usuarios")
public class UsuarioResource {
    
    @GET
    @Path("/listar")
    @Produces("application/json")
    public String listarUsuarios(){
        List<Usuario> lista;
        UsuarioDao usuarioDao = new UsuarioDao();
        lista = usuarioDao.listar();    
        Gson g = new Gson();
        System.out.println("lista:>"+lista);
        return g.toJson(lista);
    }
    
    @GET
    @Path("/usuario/{id}")
    @Produces("application/json")
    public String listarUsuarioPorId(@PathParam ("id") String id) {        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.listarPorId(id);        
        Gson g = new Gson();
        return g.toJson(usuario.toString());
    }
    /*
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello World RESTful Jersey"
                + "</title>" + "<body><h1>" + "Hello World RESTful Jersey"
                + "</body></h1>" + "</html> ";
    }
*/
}
