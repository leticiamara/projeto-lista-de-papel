package br.ufc.quixada.es.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.modelo.Usuario;

public class TesteListaDePapel {
	private TarefaDAO tarefaDao;
	private UsuarioDAO usuarioDao;
	private Usuario usuario;
	private Tarefa tarefa;

	@Before
	public void init() {
		tarefaDao = new TarefaDAO();
		usuarioDao = new UsuarioDAO();
		tarefa = new Tarefa();
		usuario = new Usuario();
	}
	
	@Test
	public void insertTarefaToComSucesso() {
		usuario = usuarioDao.selectUsuarioById(12);
		
		tarefa.setNome("teste");
		tarefa.setStatus("to");
		tarefa.setUsuario(usuario);
		
		boolean resposta = tarefaDao.insert(tarefa);
		tarefaDao.delete(tarefaDao.selectUnicoRecurso("teste"));
		
		assertEquals(true, resposta);
	}
}
