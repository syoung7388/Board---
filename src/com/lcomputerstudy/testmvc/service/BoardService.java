package com.lcomputerstudy.testmvc.service;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.dao.BoardDAO;

public class BoardService {
	private static BoardService service= null;
	private static BoardDAO dao= null;
	
    private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if(service== null) {
			service = new BoardService();
			dao= BoardDAO.getInstance();
		}
		return service;
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public  ArrayList<Board> getBoards(int page){
		return dao.getBoards(page);
	}
	
	public Board getBoard(int b_num) {
		return dao.selectBoard(b_num);
	}
	
	public String getDate() {
		return dao.getDate();
	}
	
	public void editBoard(Board board) {
		dao.editBoard(board);
		
	}
	
	public void deleteBoard(Board board) {
		dao.deleteBoard(board);
	}
	
	public void Hits(int b_num) {
		dao.Hits(b_num);
	}
	
	
	public void Comment(Board board) {
		dao.Comment( board);
	}
	
	
	public ArrayList<Board> getComment(int b_num) {
		return dao.getComment(b_num);
		
	}
	
	public int BoardCount() {
		return dao.BoardCount();
	}



}
