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

@WebServlet("/ConcluirTarefa")
public class ConcluirTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {		
		this.doGet(req, resp);

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		TarefaDAO daoTarefa = new TarefaDAO();
		long idTarefa = Long.parseLong(id);
		Tarefa tarefa = daoTarefa.selectTarefaById(idTarefa);
		if(tarefa.getStatus().equals("to")){
			tarefa.setStatus("done");
			daoTarefa.update(tarefa);
		}else {
			tarefa.setStatus("to");
			daoTarefa.update(tarefa);
		}
		PrintWriter out = response.getWriter();
		out.write(""+tarefa.getStatus());

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		processRequest(req, resp);

	}
}
