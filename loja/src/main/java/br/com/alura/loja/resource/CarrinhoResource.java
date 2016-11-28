/**
 * 
 */
package br.com.alura.loja.resource;

import java.math.BigDecimal;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.daoBD.CarrinhoDaoBD;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

/**
 * @author Thiago Silveira
 *
 */
@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id")long id){
//		Carrinho carrinho = new CarrinhoDAO().busca(id);	COMO ESTAVA ANTES DO MEU DAO
//		return carrinho.toXML();
		Carrinho carrinho = new Carrinho(new CarrinhoDaoBD().procurarPorCodigo(id));
		return carrinho.toXML();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo){
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();		
	}
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		if(produtoId ==0) return Response.notModified().build();
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	//analisar esse m�todo pois n�o estou usando o par�metro de id do produto
	@Path("{id}/produtos/{produtoId}/quantidade")
	@PUT
	public Response alteraProduto(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Response statusCode = Response.notModified().build();
		if(carrinho != null){
			Produto produto = (Produto) new XStream().fromXML(conteudo);
			for (Produto p : carrinho.getProdutos()) {
				if(p.getId() == produtoId){
					carrinho.troca(produto);
					new CarrinhoDaoBD().atualizaProduto(produto,carrinho.getId());
					statusCode = Response.accepted().build(); 
				}
			}
		}
		return statusCode;
	}
	
}
