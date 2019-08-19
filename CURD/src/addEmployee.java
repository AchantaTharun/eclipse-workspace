

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
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
          
        String ename=request.getParameter("ename");  
        String pword=request.getParameter("pword");  
        String email=request.getParameter("mail");  
        String job=request.getParameter("job");  
        
        String sa=request.getParameter("sal");
        String phno=request.getParameter("phno");  
        //out.println(email);
      //  out.println(name);
        int i=0;
        
        
        if(ename=="" || pword=="" || email=="" || job=="" ||sa=="" ||phno=="") {
        	RequestDispatcher rd=request.getRequestDispatcher("index.html");
        	rd.include(request, response);
        	
        	out.println("please fill all details");
        	
        }else {
        	try{   
        		
        		int sal=  Integer.parseInt(sa);
    			Class.forName("oracle.jdbc.driver.OracleDriver");  
    		    
    			Connection con=DriverManager.getConnection(  
    			"jdbc:oracle:thin:@192.168.4.187:1521:db122","xxsplashtesting","xxsplashtesting");  
    			 
    			Statement stmt=con.createStatement();  
    			 
    			i=stmt.executeUpdate("insert into emp_test(ename,job,sal,email,phno,password) values('"+ename+"','"+job+"','"+sal+"','"+email+"','"+phno+"','"+pword+"')");    
    			if(i==1) {
    				RequestDispatcher rd=request.getRequestDispatcher("index.html");
    	        	rd.include(request, response);
    				out.println("Inserted successfully");
    			}else {
    				RequestDispatcher rd=request.getRequestDispatcher("index.html");
    	        	rd.include(request, response);
    				out.println("Insertion failed");
    			}
    			con.close();  
    			 
    			}catch(Exception e){ System.out.println(e);}  
        	out.println("<br><br><a href='home.html'>back</a>");
    			  
        	
        }
        
    			

	}

}
