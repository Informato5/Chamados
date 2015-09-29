package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/ListarChamado")
public class ListarChamadoServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();	

		try{

			Class.forName("com.mysql.jdbc.Driver");

			String SQL = "SELECT * FROM chamados"; 

			try {


				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");
				Statement stm = conn.createStatement();

				ResultSet rs = stm.executeQuery(SQL);

				out.println("<table width = '100%>'");

				out.println("<tr bgcolor = '#c0c0c0'>");
				out.println("<td>ID</td>");
				out.println("<td>Titulo</td>");
				out.println("<td>Acoes</td>");	
				out.println("</tr>");


				while (rs.next()) {
					//out.println("<tr>");
					out.println("<td>" + rs.getInt("id") + "</td>");
					out.println("<td>" + rs .getString("titulo") + "</td>");
					out.println("<td>[x] - [0]</td>");
					out.println("</tr>");
				}
				out.println("</table>");

				stm.close();
				conn.close();

			} catch (SQLException e) {				
				e.printStackTrace();
				out.println(e);
			}

		}catch (ClassNotFoundException ex){
			out.println("Problemas ao carregar driver!!!!");
		}

	}

}


