package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditarChamado")
public class EditarChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public EditarChamadoServlet() {
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
			String SQL = "INSERT INTO chamados (titulo,conteudo) VALUES (?,?)"; 
			
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");
				PreparedStatement pstm = conn.prepareStatement(SQL);
				pstm.setString(1, titulo);
				pstm.setString(2, conteudo);
				pstm.execute();
				pstm.close();
				conn.close();
				
			} catch (SQLException e) {				
				e.printStackTrace();
				out.println(e);
			}
			
			}catch (ClassNotFoundException ex){
				out.println("Problemas ao carregar driver!!!!");
			}
		}
		
		
		out.println(conteudo);
		//out.println(e);
		

	}

}
