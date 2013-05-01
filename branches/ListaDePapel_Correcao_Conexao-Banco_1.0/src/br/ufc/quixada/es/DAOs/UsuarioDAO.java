package br.ufc.quixada.es.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import br.ufc.quixada.es.modelo.Usuario;
import br.ufc.quixada.es.persistencia.CriarTabelas;

public class UsuarioDAO {

	public boolean insert(Usuario usuario){
		boolean inserir = false;
		Session sessao = CriarTabelas.prepararSessao();
		
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
		Session sessao = CriarTabelas.prepararSessao();
		
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
		Session sessao = CriarTabelas.prepararSessao();
		
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
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return usuario;
	}

	public Usuario selectUsuarioById(long idUsuario){
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", idUsuario));
		
		Usuario usuario = (Usuario) criteria.uniqueResult();

		trasaction.commit();
		
		sessao.close();
		
		return usuario;
	}

	public List<Usuario> select() {
		Session sessao = CriarTabelas.prepararSessao();
		Transaction trasaction = sessao.beginTransaction();
			
		Criteria criteria = sessao.createCriteria(Usuario.class);
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) criteria.list();

		trasaction.commit();
		
		sessao.close();
		
		return usuarios;
	}


}
