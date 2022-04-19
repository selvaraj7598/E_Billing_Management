package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("uname");
		String pass = request.getParameter("upass");
		PrintWriter out = response.getWriter();
		
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","selva");
			Statement state =con.createStatement();
			ResultSet res = state.executeQuery("Select * from userdetails");
			while(res.next())
			{
				
				if((email.equalsIgnoreCase(res.getString(2)))&&pass.equalsIgnoreCase(res.getString(4)))
				{
					String name = res.getString(1);
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					response.sendRedirect("Personal.html");				
				}
				else
				{
						response.sendRedirect("index.html");
				}
			}
			res.close();
			state.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	
}
