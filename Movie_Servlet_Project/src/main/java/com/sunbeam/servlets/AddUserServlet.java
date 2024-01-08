package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UsersDaoImpl;
import com.sunbeam.pojos.Users;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user = new Users();
		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setMobile(req.getParameter("mobile"));
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Date dob;
		try {
			dob = new Date(sdf.parse(req.getParameter("dob")).getTime());
			user.setDob(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Login");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		
		try(UsersDaoImpl dao = new UsersDaoImpl())
		{
			int count = dao.insertUser(user);
			if(count == 1)
			{
				out.println("<h5>");
				out.println("User added successfully.");
				out.println("</h5>");
				out.println("<a href='index.html'>");
				out.println("Login");
				out.println("</a>");
			}
			else
			{
				out.println("<h5>");
				out.println("Some error has occured. Please try again...");
				out.println("</h5>");
				out.println("<a href='register.html'>");
				out.println("Login Again");
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
