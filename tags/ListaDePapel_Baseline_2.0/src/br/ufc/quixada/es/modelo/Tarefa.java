package br.ufc.quixada.es.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufc.quixada.es.util.ExcluirJSON;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue
	private long idTarefa;
	
	@Column
	private String nome;
	private String status;
	@ManyToOne
	@ExcluirJSON   // vai remover
	private Usuario usuario;
	
	
	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}
	public long getIdTarefa() {
		return idTarefa;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
