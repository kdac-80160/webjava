package com.subeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Voting Status");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("curUser");
		out.println("<h2>");
		out.printf("Hello %s %s \r\n",user.getFirstName(),user.getLastName());
		out.println("</h2>");
		if(user.getStatus() == 1)
		{
			out.println("<h3>");
			out.println("You have already voted.");
			out.println("</h3>");
		}
		else
		{
			try(CandidateDaoImpl cd = new CandidateDaoImpl())
			{
				int cnt = cd.incrementVote(Integer.parseInt(req.getParameter("candidate")));
				if(cnt == 0) {
					out.println("<h3>");
					out.println("Some error has occured.");
					out.println("</h3>");
				}
				else
				{
					try(UserDaoImpl usr = new UserDaoImpl())
					{
						usr.updateStatus(user.getId(), true);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					out.println("<h3>");
					out.println("Your vote is registered.");
					out.println("</h3>");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		out.println("<a href='logout'>Sign Out</a>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
