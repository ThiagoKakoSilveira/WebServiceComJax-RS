package br.com.alura.loja.modelo;

import java.util.List;

public class Contato {
	
	private String email;
	private List<Telefone> telefones;	
			
	public Contato(List<Telefone> telefones, String email) {
		this.telefones = telefones;
		this.email = email;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//receber telefone e adcionar na lista
		
}
