package br.ufc.quixada.es.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.modelo.Usuario;

@WebServlet("/DeletarTarefa")
public class DeletarTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {		
		this.doGet(req, resp);

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		
		/*
		 * Deletando tarefa recebida no Banco
		 */
		TarefaDAO daoTarefa = new TarefaDAO();
		
		long idTarefa = Long.parseLong(id);
		
		Tarefa tarefa = daoTarefa.selectTarefaById(idTarefa);
		daoTarefa.delete(tarefa);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		processRequest(req, resp);

	}
}
