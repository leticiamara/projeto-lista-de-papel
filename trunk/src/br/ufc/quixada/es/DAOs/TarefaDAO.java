package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class TarefaDAO {
	
	public boolean insert(Tarefa tarefa){
		boolean inserir = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			sessao.save(tarefa);
			trasaction.commit();
			inserir = true;
			
		} catch (HibernateException e) {
			System.out.println("Excecao Hibernate: " + e.getMessage() + " :: "
					+ e.toString());
		}
		finally{
			sessao.close();
			return inserir;
		}
	}

	public boolean delete(Tarefa tarefa) {
		
		boolean deletar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			
			Tarefa tarefaCarregada = (Tarefa) sessao.load(Tarefa.class, tarefa.getIdTarefa());
			
			sessao.delete(tarefaCarregada);
			
			trasaction.commit();
			deletar = true;
		}catch (HibernateException e) {
			
		}
		finally{

			sessao.close();
			return deletar;
		}
		
	}

	public boolean update(Tarefa novoTarefa) {
		
		boolean atualizar = false;
		Session sessao = CriarTabelas.prepararSessao();
		
		try{Transaction trasaction = sessao.beginTransaction();
		
		Tarefa novaTarefaCarregada = (Tarefa) sessao.load(Tarefa.class, novoTarefa.getIdTarefa());
				
		novaTarefaCarregada = novoTarefa;
		
		sessao.update(novaTarefaCarregada);
		
		trasaction.commit();
		atualizar = true;
		
		}catch (HibernateException e) {
			
		}
		finally{
			sessao.close();
			return atualizar;
		}
		
	}

	public Tarefa selectUnicoRecurso(String nomeTarefa){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("nome", nomeTarefa));
		
		Tarefa tarefa = (Tarefa) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return tarefa;
	}
	
	public List<Tarefa> select() {
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) criteria.list();

		trasaction.commit();
		
		sessao.close();
		
		return tarefas;
	}
	
	public List<Tarefa> selectDone(){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("status", "done"));
		
		ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) criteria.list();
		
		trasaction.commit();
		
		sessao.close();
		
		return tarefas; 
	}
	
	public List<Tarefa> selectTo(){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("status", "to"));
		
		ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) criteria.list();
		
		trasaction.commit();
		
		sessao.close();
		
		return tarefas; 
	}
	
	public List<Tarefa> selectDo(){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
		
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("status", "do"));
		
		ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) criteria.list();
		
		trasaction.commit();
		
		sessao.close();
		
		return tarefas; 
	}
	
				
}
