package br.ufc.quixada.es.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/ServletUsuario")

public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequestCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeUsuario = request.getParameter("nome");
		String emailUsuario = request.getParameter("email");
		String senhaUsuario = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUsuario);
		usuario.setEmail(emailUsuario);
		usuario.setSenha(senhaUsuario);
		
		UsuarioDAO daoUsuario = new UsuarioDAO();
		daoUsuario.insert(usuario);
		
		HttpSession session = request.getSession();
		session.setAttribute("USUARIO", usuario);
		
		RequestDispatcher rd = request.getRequestDispatcher("tarefas.html");
		rd.forward(request, response);
	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		processRequestCadastro(req, resp);
								
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		this.doGet(req, resp);
								
	}

}
