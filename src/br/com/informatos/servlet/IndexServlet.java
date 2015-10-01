package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Index")
public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		try {
			PrintWriter out = res.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Sistema de Chamado</title>");
			out.println("</head>");
			out.println("<body>");	
			out.println("<h1>Sistema de Chamado</h1>");
			out.println("<hr/>");
			out.println("<a href='http://localhost:8080/Chamados/NovoChamado'> Novo Chamado </a>");			
			out.println("<br>");
			out.println("<a href='http://localhost:8080/Chamados/ListarChamado'> Listar Chamado </a>");
			out.println("<br>");
			out.println("<a href='/Logoff'> Sair </a>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res){

	}



}
