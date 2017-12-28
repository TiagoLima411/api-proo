/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import bo.UsuarioBo;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import dao.UsuarioDao;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import reports.UsuariosReport;
import vo.Usuario;
/**
 *
 * @author tiago
 */
@Path("/usuarios")
public class UsuarioResource {

    @POST
    @Consumes("application/json")
    @Path("/inserir")
    public Response insertCliente(String content) {
        String message = null;        
        try {
            Gson g = new Gson();
            Usuario usuario = (Usuario) g.fromJson(content, Usuario.class);
            UsuarioBo usuarioBo = new UsuarioBo();
            message = usuarioBo.validarUsuario(usuario);
            System.out.println(message);
            return Response.ok().entity(g.toJson(message)).build();            
        } catch (Exception e) {            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }

    }

    @GET
    @Path("/listar")
    @Produces("application/json")
    public Response listarUsuarios() {
        try {
            List<Usuario> lista;
            UsuarioDao usuarioDao = new UsuarioDao();
            Gson g = new Gson();
            lista = usuarioDao.listar();            
            System.out.println("lista:>" + lista);
            return Response.ok(g.toJson(lista)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/usuario/{cpf}")
    @Produces("application/json")
    public Response listarUsuarioPorCPF(@PathParam("cpf") String cpf) {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.listarPorCpf(cpf);
            Gson g = new Gson();
            return Response.ok(g.toJson(usuario)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @PUT
    @Consumes("application/json")
    @Path("/atualizar/{cpf}")
    public Response updateUsuario(String content, @PathParam("cpf") String cpf) {
        try {
            Gson g = new Gson();
            Usuario usuario = (Usuario) g.fromJson(content, Usuario.class);

            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.atualizarPorCpf(cpf, usuario);

            Usuario usuarioResp;
            usuarioResp = usuarioDao.listarPorCpf(cpf);

            return Response.ok(g.toJson(usuarioResp)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Path("/remove/{cpf}")
    public Response removeCliente(@PathParam("cpf") String cpf) {
        try {
            Usuario usuario = new UsuarioDao().listarPorCpf(cpf);
            if (usuario != null) {
                new UsuarioDao().removerPorCpf(cpf);
            }
            Gson g = new Gson();
            return Response.ok(g.toJson(usuario)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/relatorio")
    @Produces("application/pdf")
    public StreamingOutput generate() {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                try {
                    UsuariosReport report = new UsuariosReport();
                    report.generate(output);
                } catch (DocumentException e) {
                    throw new IOException("error generating PDF", e);
                }
            }
        };
    }
}
