package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.modelo.Usuario;
import br.ufc.quixada.es.persistencia.CriarTabelas;
import br.ufc.quixada.es.persistencia.PreparaSessao;

public class UsuarioDAO {
	
	private static Session sessao;

	public boolean insert(Usuario usuario){
		boolean inserir = false;
		sessao = (Session) PreparaSessao.pegarSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			sessao.save(usuario);
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
	
	public boolean delete(Usuario usuario) {
		
		boolean deletar = false;
		sessao = (Session) PreparaSessao.pegarSessao();
		
		try{
			Transaction trasaction = sessao.beginTransaction();
			
			Usuario usuarioCarregado = (Usuario) sessao.load(Usuario.class, usuario.getIdUsuario());
			
			sessao.delete(usuarioCarregado);
			
			trasaction.commit();
			deletar = true;
		}catch (HibernateException e) {
			
		}
		finally{

			sessao.close();
			return deletar;
		}
		
	}
	
	public boolean update(Usuario novoUsuario) {
		
		boolean atualizar = false;
		sessao = (Session) PreparaSessao.pegarSessao();
		
		try{Transaction trasaction = sessao.beginTransaction();
		
		Usuario novoUsuarioCarregado = (Usuario) sessao.load(Usuario.class, novoUsuario.getIdUsuario());
				
		novoUsuarioCarregado = novoUsuario;
		
		sessao.update(novoUsuarioCarregado);
		
		trasaction.commit();
		atualizar = true;
		
		}catch (HibernateException e) {
			
		}
		finally{
			sessao.close();
			return atualizar;
		}
		
	}
	
	public Usuario selectUnicoUsuario(String email){
		sessao = (Session) PreparaSessao.pegarSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return usuario;
	}

	public Usuario selectUsuarioById(long idUsuario){
		sessao = (Session) PreparaSessao.pegarSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return usuario;
	}

	public List<Usuario> select() {
		sessao = (Session) PreparaSessao.pegarSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) criteria.list();

		trasaction.commit();
		
		sessao.close();
		
		return usuarios;
	}
	
	public Tarefa selectTarefaUsuarioById(long idTarefa, long idUsuario){
		sessao = (Session) PreparaSessao.pegarSessao();
		Transaction trasaction = sessao.beginTransaction();
		Tarefa tarefa = new Tarefa();
			
		Criteria criteria = sessao.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();
		List<Tarefa> listaTarefas = usuario.getTarefas();
		
		for (Tarefa tarefa2 : listaTarefas) {
			if(tarefa2.getIdTarefa() == idTarefa){
				tarefa = tarefa2;
			}
		}

		trasaction.commit();
		
		sessao.close();
		
		return tarefa;
	}
	
	public void excluirTarefaDeUsuario(long idTarefa, long idUsuario){
		sessao = (Session) PreparaSessao.pegarSessao();
		
		Transaction trasaction = sessao.beginTransaction();
		
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();
		List<Tarefa> listaTarefas = usuario.getTarefas();
		
//		for (Tarefa tarefa2 : listaTarefas) {
//			if(tarefa2.getIdTarefa() == idTarefa){
//				listaTarefas.remove(tarefa2);
//			}
//		}

		UsuarioDAO dao = new UsuarioDAO();
		dao.update(usuario);
		
		trasaction.commit();
		
		sessao.close();
	}
	
	public List<Tarefa> selecionarTarefasDoUsuario(Usuario usuario){
		sessao = (Session) PreparaSessao.pegarSessao();
		
		//Transaction trasaction = sessao.beginTransaction();
		List<Tarefa> listaTarefas =  sessao.createCriteria(Tarefa.class).add(Restrictions.eq("usuario", usuario)).list();
		sessao.close();
		
		return listaTarefas;
	}


}
