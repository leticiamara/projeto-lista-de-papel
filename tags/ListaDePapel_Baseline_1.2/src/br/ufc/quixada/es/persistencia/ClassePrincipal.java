package br.ufc.quixada.es.persistencia;

import java.util.List;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.modelo.Tarefa;



public class ClassePrincipal {


	public static void main(String[] args) {
		
		
		CriarTabelas.reiniciaBanco();
		
		//Cadastrar uma tarefa
		TarefaDAO dao = new TarefaDAO();
		
		Tarefa t = new Tarefa();
		t.setNome("Fazer questoes de concorrencia"); 
		t.setStatus("to");
		
		
		dao.insert(t);
		
		
		
//		List<Tarefa> tarefas = dao.selectTo();
//		
//		for (Tarefa ta : tarefas)
//		{
//			System.out.println(ta.getNome());
//		}
		
		
		

		
		
}
}
