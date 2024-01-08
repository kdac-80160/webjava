package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Users;

public class UsersDaoImpl extends Dao implements UsersDao {

	public UsersDaoImpl() throws Exception {
		
	}

	@Override
	public int insertUser(Users user) {
		String sql = "insert into users values(default,?,?,?,?,?,?)";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getMobile());
			ps.setDate(6, user.getDob());
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateUser(Users user) {
		String sql = "update users set first_name = ?, last_name = ?, mobile = ?, dob  = ? where user_id = ?";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getMobile());
			ps.setDate(4, user.getDob());
			ps.setInt(5, user.getId());
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int changePassword(Users user) {
		String sql = "update users set password = ? where user_id = ?";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, user.getPassword());
			ps.setInt(2, user.getId());
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int findUser(Users user) {
		String sql = "select * from users where email = ?";
		int id = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, user.getEmail());
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				if(set.getString("password").equals(user.getPassword()))
				{
					id = set.getInt("user_id");
					return id; //actual id
				}
				else
				{
					return -1; // wrong password
				}
			}
			return id; // 0, email not found
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Users> getSharedUsers(int id) {
		String sql = "select user_id, first_name, last_name, email from users where user_id!=?";
		List<Users> list = new ArrayList<Users>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Users user = new Users();
				user.setId(set.getInt(1));
				user.setFirstName(set.getString(2));
				user.setLastName(set.getString(3));
				user.setEmail(set.getString(4));
				list.add(user);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Users findUserObject(int userId) {
		String sql = "select * from users where user_id = ?";
		Users user = null;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, userId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
					user = new Users();
					user.setId(set.getInt("user_id"));
					user.setFirstName(set.getString("first_name"));
					user.setLastName(set.getString("last_name"));
					user.setEmail(set.getString("email"));
					user.setPassword(set.getString("password"));
					user.setMobile(set.getString("mobile"));
			}
			return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
