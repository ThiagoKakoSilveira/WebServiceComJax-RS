/**
 * 
 */
package br.com.alura.loja.resource;

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

//import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
//import br.com.alura.loja.daoBD.CarrinhoDaoBD;
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
	public Carrinho busca(@PathParam("id")long id){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		return carrinho.toXML(); EM BAIXO É USANDO JAXB 
		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho){//ANTES AQUI SE RECEBIA A STRING conteudo
//		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);	
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
	
	@Path("{id}/produtos/{produtoId}/quantidade")
	@PUT
	public Response alteraProduto(Produto produto, @PathParam("id") long id, @PathParam("produtoId") long produtoId){//ANTES AQUI SE RECEBIA A STRING conteudo
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
//		Response statusCode = Response.notModified().build(); ESBOSSO DO Q QUERIA FAZER COM BANCO ALTERANDO O PRODUTO TODO.
//		if(carrinho != null){
//			Produto produto = (Produto) new XStream().fromXML(conteudo);ANTES SE MONTAVA O PRODUTO COM A STRING EMBAIXO COM JAXB			
//			for (Produto p : carrinho.getProdutos()) {
//				if(p.getId() == produtoId){
//					carrinho.troca(produto);
//					new CarrinhoDaoBD().atualizaProduto(produto,carrinho.getId());
//					statusCode = Response.accepted().build(); 
//				}
//			}
//		}
		return Response.ok().build();
	}
	
}
