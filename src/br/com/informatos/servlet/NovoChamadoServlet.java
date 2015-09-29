package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NovoChamado")
public class NovoChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public NovoChamadoServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Novo Chamado</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Preencha as Informacoes do Chamado </h1>");
			out.println("<hr/>");
			out.println("<form method = 'POST'>");
			out.println("Titulo:<br> <input type='text' name='txttitulo'>");
			out.println("<br>");
			out.println("Conteudo: <br> <textarea name = 'txtConteudo' rows ='10' cols = '40'></textarea>");
			out.println("<br>");
			out.println("<input type = 'submit' value = 'Abrir Chamado'>");
			out.println("</form>"); 			
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String titulo = request.getParameter("txttitulo");
		String conteudo = request.getParameter("txtConteudo");
		
		if (titulo.trim().length() < 5){
			out.println("Preencha o campo titulo");
		}else if(conteudo.trim().length() <5){
			out.println("Preencha o campo conteudo");
		}else{
			try{
							
			Class.forName("com.mysql.jdbc.Driver");
			}catch (ClassNotFoundException ex){
				out.println("Problemas ao carregar driver!!!!");
			}
		}
		
		
		out.println(conteudo);
		

	}

}
