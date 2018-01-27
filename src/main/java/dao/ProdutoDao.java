/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.List;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import util.MongoConnection;
import vo.Produto;
import vo.Usuario;

/**
 *
 * @author tiago
 */
public class ProdutoDao {
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    MongoCollection<Produto> collection = MongoConnection.getInstance().getDB()
                    .getCollection("Produtos", Produto.class)
                    .withCodecRegistry(pojoCodecRegistry);
    
    public void salvar(Produto produto) {                    
        collection.insertOne(produto);
        System.out.println("Salvo:>"+produto);
    }
    
    public void salvarLista(List produto){        
        collection.insertMany(produto);                
    }
    
    public List<Produto> listar() {
        
        List<Produto> listaProdutos = new ArrayList<Produto>();                        
        for (Produto produto : collection.find()) {
            listaProdutos.add(produto);
            System.out.println("List:>"+produto);
        }        
        return listaProdutos;
    }
    
    public Produto listarPorCodProd(String codProd){
        Produto produto = null;
        produto = collection.find(eq("CodProd", codProd)).first();                            
        return produto;
    }
    
    public void atualizarPorCodProd(String codProd, Produto produto){
        try{            
            collection.replaceOne(eq("CodProd", codProd), produto);            
        }catch(Exception ex){
            System.out.println("Não foi possível atualizar usuario "+ex.getMessage());
        }
    }
    
    public void removerPorCodProd(String codProd){
        try{
            collection.deleteOne(eq("codProd", codProd));
        }catch(Exception ex){
            System.out.println("Não foi possível deletar usuario"+ex.getMessage());
        }
    }
    
    public void removerPorAtributo(String atributo, String valor){        
        try{
           collection.deleteMany(eq(atributo, valor));
        }catch(Exception ex){
            System.out.println("Atributo inválido"+ex.getMessage());
        }                
    }
}
