/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bo.ProdutoBo;
import bo.UsuarioBo;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import dao.ProdutoDao;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import reports.ProdutoReport;
import reports.UsuariosReport;
import vo.Produto;
import vo.Usuario;

/**
 * REST Web Service
 *
 * @author tiago
 */
@Path("/")
public class ProdutoResource {

    @Context
    private UriInfo context;

    public ProdutoResource() {
    }

    @POST
    @Path("/inserir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCliente(String content) throws Exception {
        String message = null;
        try {
            Gson g = new Gson();
            ProdutoBo produtoBo = new ProdutoBo();
            Produto produto = (Produto) g.fromJson(content, Produto.class);

            message = produtoBo.salvarProduto(produto);

            Map objMessage;
            objMessage = ConvertMap.converterToMap(message);
            return Response.ok().entity(g.toJson(objMessage)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message + " " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/listar")
    @Produces("application/json")
    public Response listarUsuarios() {
        try {
            List<Produto> lista;
            Gson g = new Gson();
            ProdutoBo usuarioBo = new ProdutoBo();
            lista = usuarioBo.listarProdutos();
            return Response.ok(g.toJson(lista)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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
                    ProdutoReport report = new ProdutoReport();
                    report.generate(output);
                } catch (DocumentException e) {
                    throw new IOException("error generating PDF", e);
                }
            }
        };
    }
}
