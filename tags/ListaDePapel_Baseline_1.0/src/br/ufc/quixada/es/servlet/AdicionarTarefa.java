package br.ufc.quixada.es.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.modelo.Tarefa;

@WebServlet("/AdiconarTarefa")
public class AdicionarTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {		
		this.doGet(req, resp);

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomeTarefa = request.getParameter("nome");
		Tarefa tarefa = new Tarefa();
		tarefa.setNome(nomeTarefa);
		
		//Salvando tarefa recebida no Banco
		TarefaDAO daoTarefa = new TarefaDAO();
		daoTarefa.insert(tarefa);
		
		PrintWriter out = response.getWriter();
		out.write(""+tarefa.getIdTarefa());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		processRequest(req, resp);

	}
}