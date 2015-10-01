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
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Login</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Preencha Seu Login </h1>");
			out.println("<hr/>");
			out.println("<form method = 'POST'>");
			out.println("Titulo:<br> <input type='text' name='txtLogin'>");
			out.println("<br>");
			out.println("Titulo:<br> <input type='password' name='txtSenha'>");
			out.println("<br>");
			out.println("<input type = 'submit' value = 'Logar'>");
			out.println("</form>"); 			
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String senha = request.getParameter("txtSenha");
		String login = request.getParameter("txtLogin");

		if (senha.trim().length() < 5){
			out.println("Preencha o campo senha");
		}else if(login.trim().length() <5){
			out.println("Preencha o campo login");
		}else{
			try{

				Class.forName("com.mysql.jdbc.Driver");				
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/chamado","root","informatos");

				String SQL = "SELECT * FROM usuarios WHERE login = ? and senha = ?"; 

				PreparedStatement pstm = conn.prepareStatement(SQL);
				pstm.setString(1, login);
				pstm.setString(2, senha);
				ResultSet rs = pstm.executeQuery();
				pstm.close();
				conn.close();


				if(rs.next()){
					HttpSession sessao = request.getSession();
					sessao.setAttribute("login", login);
					response.sendRedirect("http://localhost:8080/Chamados/Index");						
				}else{
					response.sendRedirect("http://localhost:8080/Chamados/Login");
				}




			} catch (SQLException e) {				
				e.printStackTrace();
				out.println(e);
			}catch (ClassNotFoundException ex){
				out.println("Problemas ao carregar driver!!!!");
			}
		}

	}
}

