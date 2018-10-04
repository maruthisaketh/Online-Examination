import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class results extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body align = \"center\">");
		out.println("<p>YOU have already taken the test<br>");
		out.println("</body>");
		out.println("</html>");
	}
}