package br.ufc.quixada.es.modelo;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Usuario {
		

	@Id
	@GeneratedValue
	private long idUsuario;
		
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String senha;	
	@OneToMany(mappedBy="usuario")
	private Set<Tarefa> tarefas;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	public Set<Tarefa> getTarefas() {
		return tarefas;
	}
		
					
}
