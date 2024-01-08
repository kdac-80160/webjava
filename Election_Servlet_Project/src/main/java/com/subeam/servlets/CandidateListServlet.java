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

@WebServlet("/candlist")
public class CandidateListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Candidate List");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='vote'>");
		try(CandidateDaoImpl cd = new CandidateDaoImpl())
		{
			List<Candidate> list = cd.findAll();
			for(Candidate c : list)
			{
			out.printf("<input type='radio' name='candidate' value='%d'/> %s - %s <br/>\r\n", c.getId(), c.getName(), c.getParty());
			}
			out.println("<input type='submit' value='Vote'/>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
