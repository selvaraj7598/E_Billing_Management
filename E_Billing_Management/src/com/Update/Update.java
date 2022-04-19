package com.Update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	
	public void init()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","selva");
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
       
   		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
   			String selectedValue=request.getParameter("isTitles");
   			
   			String value = request.getParameter("value");
   			String value1 = request.getParameter("value1");
   			System.out.println("Selected value is :- "+selectedValue);
   			if(selectedValue.equals("eid") || selectedValue.equals("address")|| selectedValue.equals("location"))
   			{
   				System.out.println("yes enter into the if case :"+selectedValue);
   				try {
   					Statement st = con.createStatement();
					int i = st.executeUpdate("update personal set '"+selectedValue+"' = '"+value1+"'Where '"+selectedValue+"' = '"+value+"'");
					System.out.println("Successfully inserted");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			}
   			}
   		
   		public void destory()
   		{
   			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		}

}
