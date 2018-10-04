import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class rules extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username	= (String)session.getAttribute("username");
		session.setAttribute("username",username);
		out.println("<html>");
		out.println("<body>");
		out.println("<div>");
		out.println("<p align = \"right\">");
		out.println("Welcome<legend>"+username+"</legend>");
		out.println("</p>");
		out.println("</div>");
		out.println("<hr>");
		out.println("<div align = \"center\">");
		out.println("<h1 align = \"center\">READ THE INSTRUCTIONS CAREFULLY</h1>");
		out.println("<p align = \"center\">There will be 10 multiple choice questions with each question having 4 options.<br>");
		out.println("Out of these options there is only one option correct.<br> ");
		out.println("At the end of the test, the result will be generated.<br>");
							
		out.println("Click<b> OK </b>to start the test</p>");
		out.println("</div>");
		out.println("<div align = \"center\">");
		out.println("<form action = \"questions\">");
		out.println("<input type = \"submit\" value = \"OK\"/>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}