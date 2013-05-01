package br.ufc.quixada.es.persistencia;

import org.hibernate.classic.Session;

public class PreparaSessao {

	private static Session session = CriarTabelas.prepararSessao();

	public static Session pegarSessao(){
		if ( session == null  ) {
			session = CriarTabelas.prepararSessao(); 
		} else if ( !session.isOpen() ) {
			session = session.getSessionFactory().openSession();
		}
		return session;
	}


}
