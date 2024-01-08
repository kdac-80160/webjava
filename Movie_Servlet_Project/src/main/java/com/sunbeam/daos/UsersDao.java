package com.sunbeam.daos;

import java.util.List;

import com.sunbeam.pojos.Users;

public interface UsersDao extends AutoCloseable {
	int insertUser(Users user);
	int updateUser(Users user);
	int changePassword(Users user);
	int findUser(Users user);
	List<Users> getSharedUsers(int id);
}
