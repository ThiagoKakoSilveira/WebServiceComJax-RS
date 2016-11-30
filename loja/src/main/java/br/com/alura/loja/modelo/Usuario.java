package br.com.alura.loja.modelo;

import java.time.LocalDate;

public class Usuario {

	private long id;	
	private String nome;
	private String sobrenome;
	private String docIdentifica;
	private LocalDate dataNasc;
	private Endereco endereco;
	private Contato contato;
	
	public Usuario(String nome, String sobrenome, String cpf, LocalDate dataNasc, Endereco endereco, Contato contato) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.docIdentifica = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;		
		this.contato = contato;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDocIdentifica() {
		return docIdentifica;
	}

	public void setDocIdentifica(String cpf) {
		this.docIdentifica = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
