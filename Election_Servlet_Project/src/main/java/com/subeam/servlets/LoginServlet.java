package com.subeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		try(UserDaoImpl userDao = new UserDaoImpl())
		{
			User user = userDao.findByEmail(req.getParameter("email"));
			if(user!= null && user.getPassword().equals(req.getParameter("passwd")))
			{
				HttpSession session = req.getSession();
				session.setAttribute("curUser", user);
				if(user.getRole().equals("admin"))
					resp.sendRedirect("result");
				else
					resp.sendRedirect("candlist");
			}
			else
			{
				out.println("<html>");
				out.println("<head>");
				out.println("<title>");
				out.println("Login Failed");
				out.println("</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h5>");
				out.println("Invalid username or password.");
				out.println("</h5>");
				out.println("<a href='index.html'>");
				out.println("Login Again");
				out.println("</a>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
