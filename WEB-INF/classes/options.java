import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class options extends HttpServlet
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
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Options</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>CHOOSE AN ACTION TO PERFORM ON DATABASE</h1><br><hr>");
		out.println("<form name=\"f1\" method=\"post\" action=\"optcheck\">");
		out.println("<br><br><br><br><br><br><br><br>");
		out.println("<table align=\"center\">");
		out.println("<caption><b>Options</b></caption>");
		out.println("<tr><td><input type=\"submit\" name=\"opt\" value=\"Update\"></td></tr>");
		out.println("<tr><td><input type=\"submit\" name=\"opt\" value=\"Display\"></td></tr>");
		out.println("</body>");
		out.println("</html>");
	}
}