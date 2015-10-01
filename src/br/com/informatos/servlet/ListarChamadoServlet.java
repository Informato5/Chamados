package br.com.informatos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;






import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/ListarChamado")
public class ListarChamadoServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();	
		
		HttpSession sessao = request.getSession();
		
		if (sessao.getAttribute("login")== null){
			response.sendRedirect("http://localhost:8080/Chamados/Login");
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");
				
				if(request.getParameter("id") != null){
					int ID = Integer.parseInt(request.getParameter("id"));
					String SQLDelete = "DELETE FROM chamados WHERE id = ?";
					PreparedStatement pstm = conn.prepareStatement(SQLDelete);
					pstm.setInt(1, ID);
					pstm.execute();
				}
				
				
				String SQL = "SELECT * FROM chamados"; 
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(SQL);
				
				out.println("<table width = '100%>'");
				out.println("<tr>");
				out.println("<td>ID</td>");
				out.println("<td>Titulo</td>");
				out.println("<td>Conteudo</td>");
				out.println("<td>Editar</td>");	
				out.println("<td>Apagar</td>");	
				out.println("</tr>");
				while (rs.next()) {
					out.println("<tr>");
					out.println("<td>" + rs.getInt("id") + "</td>");
					out.println("<td>" + rs.getString("titulo") + "</td>");
					out.println("<td>" + rs.getString("conteudo") + "</td>");
					out.println("<td><a href='http://localhost:8080/Chamados/EditarChamado?id="+rs.getInt("id")+"'>[EDITAR]</td>");					
					out.println("<td><a href='http://localhost:8080/Chamados/ListarChamado?id="+rs.getInt("id")+"'>[APAGAR]</td>");					
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


