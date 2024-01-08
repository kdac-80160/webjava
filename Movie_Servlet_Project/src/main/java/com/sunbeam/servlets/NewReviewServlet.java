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


@WebServlet("/addreview")
public class NewReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Add Review");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='addreview'>");
		out.println("<table>");
		out.println("<tbody>");
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
		out.println("Review : ");
		out.println("</td>");
		out.println("<td>");
		out.println("<textarea name='review'>");
		out.println("</textarea>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'>");
		out.println("<input type='submit' value='Submit'/>");
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
		try(ReviewsDaoImpl dao = new ReviewsDaoImpl())
		{
			HttpSession session = req.getSession();
			Users user = (Users)session.getAttribute("curUser");
			Reviews review = new Reviews();
			review.setMovie_id(Integer.parseInt(req.getParameter("movie")));
			review.setRating(Integer.parseInt(req.getParameter("rating")));
			review.setReview(req.getParameter("review"));
			review.setUser_id(user.getId());
			dao.insertReview(review);
			resp.sendRedirect("reviews?type=all");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
