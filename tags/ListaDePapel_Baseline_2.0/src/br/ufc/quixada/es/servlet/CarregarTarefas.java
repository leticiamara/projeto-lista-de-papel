package br.ufc.quixada.es.servlet;

import java.beans.Encoder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.ufc.quixada.es.DAOs.TarefaDAO;
import br.ufc.quixada.es.DAOs.UsuarioDAO;
import br.ufc.quixada.es.modelo.Tarefa;
import br.ufc.quixada.es.modelo.Usuario;
import br.ufc.quixada.es.util.DataSerializer;
import br.ufc.quixada.es.util.EstrategiaExclusaoJSON;

@WebServlet("/CarregarTarefas")
public class CarregarTarefas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	@Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {		
    		this.doGet(req, resp);

    	}

    	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    		HttpSession session = request.getSession();
    		Usuario user = (Usuario) session.getAttribute("USUARIO");
    		UsuarioDAO daoUsuario = new UsuarioDAO();
    		List<Tarefa> listaTarefas = daoUsuario.selecionarTarefasDoUsuario(user);
    	    Gson gson = new GsonBuilder().setExclusionStrategies( new EstrategiaExclusaoJSON() ).create();  
    		String responseData = gson.toJson(listaTarefas);
    		System.out.println(responseData);
    		PrintWriter out = response.getWriter();
    		out.write(responseData);
    	}

    	@Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    		processRequest(req, resp);

    	}

}
