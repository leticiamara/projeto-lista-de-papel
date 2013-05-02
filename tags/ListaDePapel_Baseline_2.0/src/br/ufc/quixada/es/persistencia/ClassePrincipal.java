package br.ufc.quixada.es.persistencia;

import java.util.List;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.modelo.Usuario;



public class ClassePrincipal {


	public static void main(String[] args) {
		
		
		CriarTabelas.reiniciaBanco();
		
		//Cadastrar uma tarefa
		UsuarioDAO daoU = new UsuarioDAO();
		TarefaDAO dao = new TarefaDAO();		
				 		
		Usuario u = new Usuario();
		u.setNome("Rodrigo");
		u.setEmail("rodrigogirao7@gmail.com");
		u.setSenha("1234");
		Tarefa t = new Tarefa();
		t.setNome("Fazer questoes de concorrencia");
		t.setStatus("to");
		t.setUsuario(u);
		
		daoU.insert(u);
		dao.insert(t);
		
		
		
//		List<Tarefa> tarefas = dao.selectTo();
//		
//		for (Tarefa ta : tarefas)
//		{
//			System.out.println(ta.getNome());
//		}
		
		
		

		
		
}
}
