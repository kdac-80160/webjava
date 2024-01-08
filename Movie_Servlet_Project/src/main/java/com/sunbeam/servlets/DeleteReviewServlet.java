package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.ReviewsDaoImpl;
import com.sunbeam.daos.SharesDaoImpl;
import com.sunbeam.pojos.Users;

@WebServlet("/delete")
public class DeleteReviewServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users user = (Users)session.getAttribute("curUser");
		
		try(ReviewsDaoImpl dao = new ReviewsDaoImpl())
		{
			try(SharesDaoImpl sdao = new SharesDaoImpl())
			{
				sdao.deleteShare(Integer.parseInt(req.getParameter("id")));
			}
			dao.deleteReview(user.getId(), Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("reviews?type=all");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
