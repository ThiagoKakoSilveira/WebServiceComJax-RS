package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class ClienteTest {
	
	private HttpServer server;
//	private ClientConfig config = new ClientConfig();	
	private Client cliente = ClientBuilder.newClient(new ClientConfig().register(new LoggingFilter()));
	private WebTarget target = cliente.target("http://localhost:8080");
	
//	@BeforeClass
//	public void startaServidor(){
//		server = Servidor.inicializaServidor();
//	}
//	
//	@AfterClass
//	public void mataServidor(){
//		server.stop();
//	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona(){		
		server = Servidor.inicializaServidor();
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Santo Antonio 347", carrinho.getRua());
		server.stop();
	}
	
	@Test
	public void testaQueSuportaNovosCarrinhos(){
		server = Servidor.inicializaServidor();
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314, "Microfone", 37, 1));
		carrinho.setCidade("Porto Alegre");
		carrinho.setRua("João Salomini 715");
		String xml = carrinho.toXML();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		String conteudo = cliente.target(location).request().get(String.class);
		Assert.assertTrue(conteudo.contains("Microfone"));
		server.stop();
	}

}
