package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Movies;

public class MoviesDaoImpl extends Dao implements MoviesDao {

	public MoviesDaoImpl() throws Exception {
		
	}

	@Override
	public int searchMovie(int movieId) {
		String sql = "select * from movies where movie_id = ?";
		int id = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, movieId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				if(set.getInt("movie_id")== movieId)
				{
					id = set.getInt("movie_id");
					return id;
				}
			}
			return id;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Movies> getMovies() {
		String sql = "select * from movies";
		List<Movies> list = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Movies movie = new Movies();
				movie.setId(set.getInt("movie_id"));
				movie.setTitle(set.getString("title"));
				movie.setRel(set.getDate("release_date"));
				list.add(movie);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Movies getMovieById(int id) {
		String sql = "select * from movies where movie_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Movies movie = new Movies();
				movie.setId(set.getInt("movie_id"));
				movie.setTitle(set.getString("title"));
				movie.setRel(set.getDate("release_date"));
				return movie;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
