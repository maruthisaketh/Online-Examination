import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class optcheck extends HttpServlet
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
		
		String vopt=request.getParameter("opt");

		if(vopt.equals("Update"))
			response.sendRedirect("./update.html");
		else if(vopt.equals("Display"))
			response.sendRedirect("./display");
	}
}
