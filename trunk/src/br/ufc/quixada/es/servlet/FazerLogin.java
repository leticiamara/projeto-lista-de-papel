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

import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelo.Usuario;

@WebServlet("/FazerLogin")
public class FazerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   public void processRequestLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String emailUsuario = request.getParameter("email");
		String senhaUsuario = request.getParameter("senha");
		
		HttpSession session = request.getSession();
		UsuarioDAO usDAO = new UsuarioDAO();
		Usuario us = usDAO.selectUnicoUsuario(emailUsuario);
		
		if(us.getSenha().equals(senhaUsuario)){
			session.setAttribute("USUARIO", us);
			
			RequestDispatcher rd = request.getRequestDispatcher("tarefas.html");
			rd.forward(request, response);	
		}
																			
	}
   public void processRequestLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();		
		session.removeAttribute("USUARIO");
		
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);
																			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestLogout(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestLogin(request, response);
	}

}
