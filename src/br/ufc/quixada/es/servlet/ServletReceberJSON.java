package br.ufc.quixada.es.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.util.DataSerializer;

@WebServlet("/ServletEnviarJSON")
public class ServletReceberJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {		
		this.doGet(req, resp);

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestData = processRequest(request);	 /* A String JSOn enviada pelo JavaScript */	

		Tarefa tarefa = DataSerializer.getInstance().converterJsonParaTarefa(requestData);
		
		/*
		 * Salvando tarefa recebida no Banco
		 */
		TarefaDAO daoTarefa = new TarefaDAO();
		daoTarefa.insert(tarefa);

		}

	private String processRequest(HttpServletRequest request) {
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { e.printStackTrace(); }

		  return jb.toString();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		processRequest(req, resp);

	}


}
