import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class questions extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username	= (String)session.getAttribute("username");
		session.setAttribute("username",username);
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam?useSSL = false","root","root");
			st = con.createStatement();
			
			rs = st.executeQuery("select * from results where username = '"+username+"'");
			if(rs.next())
			{
				if(rs.getString(2)!=null)
				{
					RequestDispatcher rd = request.getRequestDispatcher("results");
					rd.forward(request,response);
				}
			}
			
			rs = st.executeQuery("select * from questions");
			out.println("<html>");
			out.println("<body>");
			out.println("<div>");
			out.println("<p align = \"right\">");
			out.println("Welcome<legend>"+username+"</legend>");
			out.println("</p>");
			out.println("</div>");
			out.println("<hr><br>");
			out.println("<form action = \"answers\" method = \"post\">");
			int i=0;
			while(rs.next())
			{
				i++;
				out.println("<div>");
				out.println("<p Q) align = \"left\"><b>"+rs.getString(1)+"</b></p><br>");
				out.println(" <input align = \"left\" type = \"radio\" name = \""+i+"\" value = \""+(String)(rs.getString(2))+"\"> "+rs.getString(2)+"<br>");
				out.println(" <input align = \"left\" type = \"radio\" name = \""+i+"\" value = \""+rs.getString(3)+"\"> "+rs.getString(3)+"<br>");
				out.println(" <input align = \"left\" type = \"radio\" name = \""+i+"\" value = \""+rs.getString(4)+"\"> "+rs.getString(4)+"<br>");
				out.println(" <input align = \"left\" type = \"radio\" name = \""+i+"\" value = \""+rs.getString(5)+"\"> "+rs.getString(5)+"<br>");
				out.println("</div>");
				out.println("<br>");
			}
			out.println("<input type = \"submit\" value = \"submit\" name = \"submit\">");
			out.println("</form>");
			out.println("</body></html>");
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