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

import com.sunbeam.daos.MoviesDaoImpl;
import com.sunbeam.daos.ReviewsDaoImpl;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("curUser");
		
		int id = user.getId();
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Reviews");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.printf("Hello! %s %s", user.getFirstName(), user.getLastName());
		out.println("<br />");
		out.println("<hr />");
		out.println("<a href='reviews?type=all'>All Reviews</a>");
		out.println(" | ");
		out.println("<a href='reviews?type=my'>My Reviews</a>");
		out.println(" | ");
		out.println("<a href='reviews?type=shared'>Shared Reviews</a>");
		
		
		String type = req.getParameter("type");
		if(type == null || type.equals("all"))
		out.println("<h3>All Reviews</h3>");
		else if(type.equals("my"))
		out.println("<h3>My Reviews</h3>");
		else
		out.println("<h3>Shared Reviews</h3>");
		
		
		out.println("<table border='1' style='text-align : center; width : 400px'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>");
		out.println("Review ID");
		out.println("</th>");
		out.println("<th>");
		out.println("Movie");
		out.println("</th>");
		out.println("<th>");
		out.println("Rating");
		out.println("</th>");
		out.println("<th>");
		out.println("Review");
		out.println("</th>");
		out.println("<th>");
		out.println("Action");
		out.println("</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		try(ReviewsDaoImpl dao = new ReviewsDaoImpl())
		{
			try(MoviesDaoImpl dao1 = new MoviesDaoImpl())
			{
				List<Reviews> list;
				if(type.equals("all")) {
				list =  dao.getReviews();
				}
				else if(type.equals("my"))
				{
					list =  dao.getReviewsById(id);
				}
				else
					list = dao.getSharedReviews(id);
				if(list != null)
				for(Reviews r : list)
				{
					out.printf("<tr>");
					out.printf("<td>%s</td>\r\n",r.getRev_id());
					out.printf("<td>%s</td>\r\n",dao1.getMovieById(r.getMovie_id()).getTitle());
					out.printf("<td>%s</td>\r\n",r.getRating());
					out.printf("<td>%s</td>\r\n",r.getReview());
					out.println("<td>");
					if(r.getUser_id() == user.getId())
					{
						out.printf("<a href='edit?id=%s'><img src='edit.png' style='height:24px;width:24px'/></a>\r\n",r.getRev_id());
						out.printf("<a href='delete?id=%s'><img src='delete.png' style='height:24px;width:24px'/></a>\r\n",r.getRev_id());
						out.printf("<a href='share?id=%s'><img src='share.png' style='height:24px;width:24px'/></a>\r\n",r.getRev_id());
					}
					out.println("</td>");
					out.printf("</tr>");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<br />");
		out.println("<a href='addreview'>Add Review</a>");
		out.println("  |  ");
		out.println("<a href='signout'>SignOut</a>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
