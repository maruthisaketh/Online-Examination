import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class adminpage extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL = false","root","root");
			st = con.createStatement();
			rs = st.executeQuery("select * from adminlogin where username = '"+username+"' and password = '"+password+"'");
			if(rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				RequestDispatcher rd = request.getRequestDispatcher("/options");
				rd.forward(request,response);
			}
			else
			{
				response.sendRedirect("adminloginservlet");
			}
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