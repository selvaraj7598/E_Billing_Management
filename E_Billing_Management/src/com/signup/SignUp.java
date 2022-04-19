package com.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "selva");
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String name1 = request.getParameter("uname");
		String email = request.getParameter("email");
		String phone = request.getParameter("uphone");

		String pass = request.getParameter("pass");

		if (name1 != null) {

			Statement state;
			try {
				state = con.createStatement();
				int i = state.executeUpdate("insert into userdetails values('" + name1 + "','" + email + "','" + phone
						+ "','" + pass + "')");

				out.println("<script type=\"text/javascript\">");
				out.println("alert('your login successfully');");
				out.println("location='index.html';");
				out.println("</script>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('plese enter the all the field');");
			out.println("location='SignUp.html';");
			out.println("</script>");
		}

	}

	public void destory() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
