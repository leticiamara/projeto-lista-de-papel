package br.ufc.quixada.es.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.quixada.es.persistencia.ClassePrincipal;

/**
 * Servlet implementation class ServletDeConfigurarEReiniciarOBanco
 */
@WebServlet("/ServletDeConfigurarEReiniciarOBanco")
public class ServletDeConfigurarEReiniciarOBanco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeConfigurarEReiniciarOBanco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClassePrincipal.main(null);
		PrintWriter out = response.getWriter();
		out.write("REINICIOU O BANCO");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
