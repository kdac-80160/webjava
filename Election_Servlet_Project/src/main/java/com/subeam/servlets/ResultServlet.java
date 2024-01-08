package com.subeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Election Result");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>");
		out.println("ID");
		out.println("</th>");
		out.println("<th>");
		out.println("Name");
		out.println("</th>");
		out.println("<th>");
		out.println("Party");
		out.println("</th>");
		out.println("<th>");
		out.println("Votes");
		out.println("</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		try(CandidateDaoImpl c = new CandidateDaoImpl())
		{
			List<Candidate> list = c.getCandidatewiseVotes();
			System.out.println(list.size());
			for(Candidate cd : list)
			{
				out.printf("<tr>");
				out.printf("<td>%s</td>\r\n",cd.getId());
				out.printf("<td>%s</td>\r\n",cd.getName());
				out.printf("<td>%s</td>\r\n",cd.getParty());
				out.printf("<td>%s</td>\r\n",cd.getVotes());
				out.printf("</tr>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href='logout'>Sign Out</a>");
		out.println("</body>");
		out.println("</html>");
	}
}
