package br.ufc.quixada.es.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.modelo.Tarefa;

import static org.mockito.Mockito.when;

public class TesteListaDePapel {
	private Tarefa tarefa;
	
	@Mock
	private TarefaDAO tarefaDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		tarefa = new Tarefa();
	}
	
	@Test
	public void insertTarefaToComSucesso() {
		when(tarefaDao.insert(tarefa)).thenReturn(true);
		boolean resposta = tarefaDao.insert(tarefa);
		
		assertEquals(true, resposta);
	}
}