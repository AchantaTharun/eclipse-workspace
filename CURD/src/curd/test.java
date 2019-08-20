package curd;


import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{  
		//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@192.168.4.187:1521:db122","xxsplashtesting","xxsplashtesting");  
			 
		//step3 create the statement object  
			Statement stmt=con.createStatement();  
			 
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select count(*)  from emp");  
			//while(rs.next())  
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			rs.next();
			System.out.println(rs.getString(1));  
			//step5 close the connection object  
			con.close();  
			 
			}catch(Exception e){ System.out.println(e);}  
			  
			} 

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
