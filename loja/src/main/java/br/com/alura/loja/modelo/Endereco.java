package br.com.alura.loja.modelo;

public class Endereco {
	private long id;
	private String cidade;
	private String rua;
	private int numero;
	private String complemento;
	
	
	public Endereco(String cidade, String rua, int numero, String complemento) {
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Endereco(){
		
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
