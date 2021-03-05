package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;


import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.User;


public class UserService{
	private static UserService service= null;
	private static UserDAO dao= null;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if(service== null) {
			service= new UserService();
			dao= UserDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<User> getUsers(int page){
		return dao.getUsers(page);
	}
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	
	public int getUsersCount() {
		return dao.getUsersCount();
	}
	
	public User loginUser(String id, String pw) {
		return dao.loginUser(id, pw);
	}
}

