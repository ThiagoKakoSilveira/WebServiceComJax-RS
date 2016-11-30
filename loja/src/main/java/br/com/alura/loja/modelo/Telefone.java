package br.com.alura.loja.modelo;

public class Telefone {
	
	private long id;
	private String prefixo;
	private String numero;
			
	public Telefone(String prefixo, String numero) {
		this.prefixo = prefixo;
		this.numero = numero;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
		
}
