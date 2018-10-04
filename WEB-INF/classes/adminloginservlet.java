import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class adminloginservlet extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ADMIN</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1 align = \"center\">LOGIN as Admin</h1>");
		out.println("<br>");
		out.println("<hr>");
		out.println("<br>");
		out.println("<form name = \"form\" action = \"adminpage\" method = \"post\">");
		out.println("<div class = \"container\" align = \"center\">");		
		out.println("<h4>Incorrect username or password</h4>");		
		out.println("<div>");		
		out.println("<label>USERNAME :</label>");			
		out.println("<input type = \"text\" name = \"username\" id = \"username\">");			
		out.println("</div>");		
		out.println("<br>");		
		out.println("<div>");		
		out.println("<label>PASSWORD :</label>");			
		out.println("<input type = \"password\" name = \"password\" id = \"password\">");			
		out.println("</div>");		
		out.println("<br>");		
		out.println("<div>");		
		out.println("<input type = \"submit\" name = \"submit\" id = \"submit\" value = \"login\">");			
		out.println("</div>");		
		out.println("</div>");	
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
