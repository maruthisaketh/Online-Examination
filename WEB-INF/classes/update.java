import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class update extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username	= (String)session.getAttribute("username");
		session.setAttribute("username",username);
		if(username == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("adminloginservlet.java");
			rd.forward(request,response);
		}
		
		String rollno = request.getParameter("Rollno");
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL = false","root","root");
			st = con.createStatement();
			int count = st.executeUpdate("update results set score = NULL where username = '"+rollno+"'");
			RequestDispatcher rd = request.getRequestDispatcher("options");
			rd.forward(request,response);
		}
		catch(SQLException sqle)
		{
			out.println("EXCEPTION : Occurred during database connection");
		}
		catch(Exception e)
		{
			out.println("Exception occurred.");
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
				if(st != null)
				{
					st.close();
				}
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception e)
			{
				out.println("Exception during closing connections");
			}
		}
	}
}