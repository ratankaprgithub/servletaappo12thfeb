package com.cts.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StudentRegisterSrv extends GenericServlet{

	private Connection conn;
	private PreparedStatement ps;

	
	@Override
	public void destroy() {
		
		if(conn != null){
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
	
	
	
	@Override
	public void init() throws ServletException {
		
		
		String dname=getInitParameter("driverName");
		String url=getInitParameter("url");
		String uname=getInitParameter("username");
		String pass=getInitParameter("password");
		
		
		
		try {
			Class.forName(dname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			 conn=DriverManager.getConnection(url, uname, pass);
			
			 ps=conn.prepareStatement("insert into student values(?,?,?)");
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void service(ServletRequest requset, ServletResponse response) throws ServletException, IOException {
		
		String sroll=requset.getParameter("roll");
		String sname=requset.getParameter("name");
		String smarks=requset.getParameter("marks");
		
		int roll=Integer.parseInt(sroll.trim());
		int marks=Integer.parseInt(smarks.trim());
		
		ServletContext ctx=getServletContext();
		
		String admin=ctx.getInitParameter("adminName");
		
		
		int x=0;
		
		
			
			try {
				ps.setInt(1, roll);
				
				ps.setString(2, sname);
				ps.setInt(3, marks);
				
				 x=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			
			
			pw.print("<body bgcolor='yellow'>");
			
			
			if(x>0){
				pw.print("<h1>Welcome : "+admin+"</h1>");
				pw.print("<h1>Record Inserted Sucessfully</h1>");
			}
			else
				pw.print("<h1>fails.....</h1>");
			
			
			pw.print("</body>");
			
			
			
			
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
