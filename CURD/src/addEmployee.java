

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addEmployee
 */
@WebServlet("/addEmployee")
public class addEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String name=request.getParameter("ename");  
        String password=request.getParameter("pword");  
        String email=request.getParameter("email");  
        String job=request.getParameter("job");  
        
        String sal=request.getParameter("sal");  
        String phno=request.getParameter("phno");  
        
      //  out.println(name);
        
        try{   
    			Class.forName("oracle.jdbc.driver.OracleDriver");  
    		    
    			Connection con=DriverManager.getConnection(  
    			"jdbc:oracle:thin:@192.168.4.187:1521:db122","xxsplashtesting","xxsplashtesting");  
    			 
    			Statement stmt=con.createStatement();  
    			 
    			ResultSet rs=stmt.executeQuery("select count(*)  from emp");    
    			rs.next();
    			System.out.println(rs.getString(1));  
    			con.close();  
    			 
    			}catch(Exception e){ System.out.println(e);}  
    			  
    			

	}

}
