package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.SharesDaoImpl;
import com.sunbeam.daos.UsersDaoImpl;
import com.sunbeam.pojos.Users;

@WebServlet("/share")
public class ShareReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("curUser");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Share Review");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='share' method='post'>");
		out.println("<table>");
		out.println("<tbody>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Review Id : ");
		out.println("</td>");
		out.println("<td>");
		out.printf("<input type='number' name='id' value='%s' readonly>", req.getParameter("id"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("User : ");
		out.println("</td>");
		out.println("<td>");
		out.println("<select name='user'>");
		try (UsersDaoImpl dao = new UsersDaoImpl()) {
			List<Users> list = dao.getSharedUsers(user.getId());
			for(Users u : list)
			{
				out.printf("<option name='user' value='%s'>%s</option\r\n>",u.getId(),u.getFirstName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</select>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'>");
		out.println("<input type='submit' value='Share'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(SharesDaoImpl sdao = new SharesDaoImpl())
		{
			sdao.shareReviews(Integer.parseInt(req.getParameter("user")), Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("reviews?type=all");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
