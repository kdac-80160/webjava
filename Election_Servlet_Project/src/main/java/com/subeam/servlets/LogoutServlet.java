package com.subeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Login Failed");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h5>");
		out.println("Thank you!");
		out.println("</h5>");
		out.println("<a href='index.html'>");
		out.println("Login Again");
		out.println("</a>");
		out.println("</body>");
		out.println("</html>");
		HttpSession session = req.getSession();
		session.invalidate();
	}
}
