package com.test;

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

@WebServlet("/check")
public class ValidationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get the print writer
		PrintWriter pw = res.getWriter();
		// set Content type
		res.setContentType("text/html");
		// read the Values
		String name = req.getParameter("username");
		String pass = req.getParameter("pass");

		// load the jdbc driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String uname = "system";
			String pwd = "tiger";
			Connection con = DriverManager.getConnection(url, uname, pwd);
			Statement st = con.createStatement();
			String query = "select count(*) from usertab where name='" + name + "' and pass='" + pass + "'";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			rs.next();
			int count = rs.getInt(1);
			if (count == 0) {
				pw.println("<h2>Invalid credentials Please try again</h2>");
			} else {
				pw.println("valid credentials");
				pw.println("<h2>Welcome "+name+"</h2>");
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// close the stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
