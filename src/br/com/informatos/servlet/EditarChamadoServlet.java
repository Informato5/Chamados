package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditarChamado")
public class EditarChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();	

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");

			String SQL = "SELECT * FROM chamados WHERE id = ?"; 
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, Integer.parseInt(request.getParameter("id")));
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Editar Chamado</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Preencha as Informacoes do Chamado </h1>");
				out.println("<hr/>");
				out.println("<form method = 'POST'>");
				out.println("ID:<br> <input type='text' name='txtId' readonly ='readonly' value = '"+rs.getInt("id")+"'>");
				out.println("<br>");
				out.println("Titulo:<br> <input type='text' name='txttitulo' value = '"+rs.getString("titulo")+"'>");
				out.println("<br>");
				out.println("Conteudo: <br> <textarea name = 'txtConteudo' rows ='10' cols = '40'>"+rs.getString("conteudo")+"</textarea>");
				out.println("<br>");
				out.println("<input type = 'submit' value = 'Atualizar Chamado'>");
				out.println("</form>"); 			
				out.println("</body>");
				out.println("</html>");
			}else{
				out.println("Esse Chamado Não Existe!!!!!!");
			}
			pstm.close();
			conn.close();

		} catch (SQLException e) {				
			e.printStackTrace();
			out.println(e);
		}catch (ClassNotFoundException ex){
			out.println("Problemas ao carregar driver!!!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("txttitulo");
		String conteudo = request.getParameter("txtConteudo");

		if (titulo.trim().length() < 5){
			out.println("Preencha o campo titulo");
		}else if(conteudo.trim().length() <5){
			out.println("Preencha o campo conteudo");
		}else{
			try{							
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");
				
				String SQL = "UPDATE chamados SET titulo = ?,conteudo = ? WHERE id = ?"; 				
				PreparedStatement pstm = conn.prepareStatement(SQL);
				pstm.setString(1, titulo);
				pstm.setString(2, conteudo);
				pstm.setInt(3, id);
				pstm.execute();
				pstm.close();
				conn.close();
				
				response.sendRedirect("http://localhost:8080/Chamados/ListarChamado");

			} catch (SQLException e) {				
				e.printStackTrace();
				out.println(e);		

			}catch (ClassNotFoundException ex){
				out.println("Problemas ao carregar driver!!!!");
			}
		}


		out.println(conteudo);
		//out.println(e);


	}


}








