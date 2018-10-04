import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class answers extends HttpServlet
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
			rs = st.executeQuery("select * from answers");
			int i=0;
			int count = 0;
			while(rs.next())
			{
				i++;
				String ans = request.getParameter(""+i);
				out.println(rs.getString(1)+ ":" + rs.getString(2));
				out.println("<br>");
				if(ans.equals(rs.getString(2)))
				{
					count ++;
				}
			}
			out.println("<hr>");
			int count1 = st.executeUpdate("update results set score = "+count+" where username = '"+username+"'");
			out.println("<html>");
			out.println("<body>");
			out.println("<p>Thank you for taking test<br>");
			out.println("you score is :"+count+"</p>");
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