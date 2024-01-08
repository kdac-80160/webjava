package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UsersDaoImpl;
import com.sunbeam.pojos.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Login");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		Users user = new Users();
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("passwd"));
		HttpSession session = req.getSession();
		
		try(UsersDaoImpl dao = new UsersDaoImpl())
		{
			int value = dao.findUser(user);
			if(value != 0 && value!=-1)
			{
				user = dao.findUserObject(value);
				session.setAttribute("curUser", user);
				resp.sendRedirect("reviews?type=all");
			}
			else if(value == -1)
			{
				out.println("<h5>");
				out.println("Wrong password entered. Try again..");
				out.println("</h5>");
				out.println("<a href='index.html'>");
				out.println("Login");
				out.println("</a>");
			}
			else
			{
				out.println("<h5>");
				out.println("User does not exist, please sign up.");
				out.println("</h5>");
				out.println("<a href='register.html'>");
				out.println("Sign Up");
				out.println("</a>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
	}
}
