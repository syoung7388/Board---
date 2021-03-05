package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;



import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet(urlPatterns = "*.do")

public class Controller extends HttpServlet{
	
	private static final long serialVersionUID= 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI= request.getRequestURI();
		String contextPath= request.getContextPath();
		String command= requestURI.substring(contextPath.length());
		String view= null;
		int page= 1;
		int count= 0;
		String id= null;
		String pw= null;
		String newdate= null;
		int b_num = 0;
		Board board = null;
	    //String b_id= null;
	
		
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		command= checkSession(request,response,command);
		
		
		
		switch(command) {
		case "/board-insert.do":
		       view = "board/insert";
		       break;
		case "/board-insert-process.do":
			   board = new Board();
			   
			   BoardService boardService= BoardService.getInstance();
			   newdate= boardService.getDate();
			   
			   board.setU_id(request.getParameter("u_id"));
		       board.setB_date(newdate);
			   board.setB_title (request.getParameter("title"));
			   board.setB_content(request.getParameter("content"));
			   
			   
			   boardService.insertBoard(board);
			   view = "board/insert-result";
			   break;
		case "/board-BoardList.do": 
			  String rebPage = request.getParameter("page");
			  if(rebPage != null)
				  page=Integer.parseInt(rebPage);
			  boardService = BoardService.getInstance();
              count= boardService.BoardCount();
			  Pagination pagination2= new Pagination(page,count);
			  
			  ArrayList<Board> list2= boardService.getBoards(page);
			  
			
			  request.setAttribute("pagination2", pagination2);
			  request.setAttribute("list2", list2);
			  view = "board/BoardList";

			  break;
			
		case "/board-listDetail.do":
			  boardService = BoardService.getInstance();
			  
			  b_num = Integer.parseInt(request.getParameter("b_num"));
			  boardService.Hits(b_num);
			  board = boardService.getBoard(b_num);
			   
			  
			  ArrayList<Board> list4= boardService.getComment(b_num);

		     
			  request.setAttribute("list4", list4);
			  request.setAttribute("board", board);
			
			   view= "board/listDetail";
			   break;
			   
		case "/board-BoardEdit.do":
		      view="board/BoardEdit";
		      break;
			   
		case "/board-edit-process.do":
			
			
			
			 boardService = BoardService.getInstance();
			 newdate= boardService.getDate();
			 board = new Board();
			 board.setB_num(Integer.parseInt(request.getParameter("b_num")));
		     board.setB_date(newdate);
			 board.setB_title (request.getParameter("title"));
			 board.setB_content(request.getParameter("content"));
			 
			
			 boardService.editBoard(board);
			 
			 view="board/edit-result";
		
		case "/board-goboardlist.do":
			
			 String regPage = request.getParameter("page");
			  if(regPage != null)
				  page=Integer.parseInt(regPage);
			 boardService = BoardService.getInstance();
			 count= boardService.BoardCount(); 
			 Pagination pagination3= new Pagination(page,count);
			 ArrayList<Board> list3= boardService.getBoards(page);
			
			 request.setAttribute("pagination2", pagination3);
			 request.setAttribute("list2", list3);

			 view = "board/BoardList";
			 break;
			 
		case "/board-BoardDelete.do":
			 boardService = BoardService.getInstance();
			 board = new Board();
			 board.setB_num( Integer.parseInt(request.getParameter("b_num")));
			 boardService.deleteBoard(board);
			 
			 view= "board/delete-result";
			 break;
			 
			 
		case "/board-comment-process.do":
			
			boardService= BoardService.getInstance();
			newdate=boardService.getDate();
			b_num = Integer.parseInt(request.getParameter("b_num"));
			board = new Board(); 
			User user= new User();
			board.setB_date(newdate);
			board.setB_num(Integer.parseInt(request.getParameter("b_num")));
			board.setC_comment(request.getParameter("c_comment"));
			board.setC_id(request.getParameter("c_id"));			
			boardService.Comment(board);
			
		    ArrayList<Board> list5= boardService.getComment(b_num);
			
		
			request.setAttribute("list5", list5);
			
			
			
			view="board/comment-list";
			break;
			
		
		case "/user-list.do":
			String reqPage= request.getParameter("page");
			if(reqPage != null)
				page= Integer.parseInt(reqPage);
			
			UserService userService=  UserService.getInstance();
			ArrayList<User> list = userService.getUsers(page);
			
			count= userService.getUsersCount();
			
			Pagination pagination = new Pagination(page, count);
			
			
			
			request.setAttribute("list",list);
			request.setAttribute("pagination", pagination);
			view = "user/list";
			break;
			
		case "/user-insert.do":
			view = "user/insert";
			break;
			
		case "/user-insert-process.do":
			user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3"));
            user.setU_age(request.getParameter("age"));	
           
            userService = UserService.getInstance();
            userService.insertUser(user);
            
            view = "user/insert-result";
            break;
            
		case "/user-login.do":
			view = "user/login";
			break;
		case "/user-login-process.do":
			
			id= request.getParameter("login_id");
			pw= request.getParameter("login_password");
			
			userService= UserService.getInstance();
			user= userService.loginUser(id,pw);
			
			if(user!= null) {
				HttpSession session= request.getSession();
				session.setAttribute("user", user);
				view = "user/login-result";
			} else {
				view= "user/login-fail";
			}
			break;
			
		case "/user-logout.do":
			HttpSession session= request.getSession();
			session.invalidate();
			
			view="user/login";
			break;
		case "/access-denied.do":
			view="user/access-denied";
			break;
		}
		
		RequestDispatcher rd= request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response,String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
				,"/board-insert.do"
         		,"/board-BoardList.do"
				,"/board-listDetail.do"
				,"/board-insert-result.bo"
		};
		
		for(String item:authList) {
			if(item.equals(command)) {
				if(session.getAttribute("user") == null) {
					command="/access-denied.do";
				}
			}
		}
		return command;
	}
	

	
	
	
	
	
}




