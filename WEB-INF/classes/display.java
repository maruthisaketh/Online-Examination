import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class display extends HttpServlet
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
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL = false","root","root");
			st = con.createStatement();
			rs = st.executeQuery("select * from answers");
			out.println("<html>");
			out.println("<body>");
			out.println("<b><u>List of questions and answers</u></b>");
			out.println("<br>");
			while(rs.next())
			{
				out.println(rs.getString(1)+ ":" + rs.getString(2));
				out.println("<br>");
			}
			out.println("<br>");
			out.println("<hr>");
			out.println("<b><u>list of students and score</u></b>");
			out.println("<br>");
			rs = st.executeQuery("select * from results");
			while(rs.next())
			{
				out.println(rs.getString(1)+ ":" + rs.getString(2));
				out.println("<br>");
			}
			out.println("</body>");
			out.println("</html>");
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