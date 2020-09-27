package rmi.auth.dominio;

import java.io.Serializable;

import rmi.auth.enums.PapelEnum;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String senha;
	private PapelEnum papel;

	public Usuario(String nome, String senha) {
		super();
		this.nome = nome;
		this.senha = senha;
	}

	public Usuario(String nome, String senha, PapelEnum papel) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.papel = papel;
	}

	public Usuario() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public PapelEnum getPapel() {
		return papel;
	}

	public void setPapel(PapelEnum papel) {
		this.papel = papel;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", papel=" + papel + "]";
	}

}
