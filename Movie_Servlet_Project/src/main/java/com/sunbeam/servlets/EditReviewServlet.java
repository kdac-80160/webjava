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
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

@WebServlet("/edit")
public class EditReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("curUser");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Edit Review");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='edit'>");
		out.println("<table>");
		out.println("<tbody>");
		out.println("<tr>");
		out.println("<td>");
		out.println("ID : ");
		out.println("</td>");
		out.println("<td>");
		out.printf("<input type='number' name='reviewid' value='%s' readonly/>\r\n", req.getParameter("id"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Movie : ");
		out.println("</td>");
		out.println("<td>");
		out.println("<select name='movie'>");
		try(MoviesDaoImpl dao = new MoviesDaoImpl())
		{
			List<Movies> list = dao.getMovies();
			for(Movies m : list)
			{
				out.printf("<option name='movie' value='%s'>%s</option>\r\n",m.getId(),m.getTitle());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</select>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Rating : ");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='number' name='rating'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("User ID : ");
		out.println("</td>");
		out.println("<td>");
		out.printf("<input type='number' name='userid' value='%s' readonly/>\r\n", user.getId());
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Review : ");
		out.println("</td>");
		out.println("<td>");
		out.println("<textarea name='review'>");
		out.println("</textarea>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'>");
		out.println("<input type='submit' value='Update'/>");
		out.println("<td>");
		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reviews review = new Reviews();
		review.setMovie_id(Integer.parseInt(req.getParameter("movie")));
		review.setRating(Integer.parseInt(req.getParameter("rating")));
		review.setReview(req.getParameter("review"));
		review.setRev_id(Integer.parseInt(req.getParameter("reviewid")));
		
		HttpSession session = req.getSession();
		Users user = (Users)session.getAttribute("curUser");
		
		try(ReviewsDaoImpl dao = new ReviewsDaoImpl())
		{
			dao.editReview(review, user.getId());
			resp.sendRedirect("reviews?type=all");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
