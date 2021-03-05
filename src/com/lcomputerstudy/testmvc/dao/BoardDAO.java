package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.database.DBConnectionB;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardDAO {
	private static  BoardDAO dao= null;

	
	private BoardDAO() {
		
	}
	public static BoardDAO  getInstance() {
		if(dao==null) {
			dao= new BoardDAO();
		}
		return dao;
	}
	
	
	public void insertBoard(Board board) {
		Connection conn= null;
		PreparedStatement pstmt= null;

		
		try {
			
			conn= DBConnectionB.getConnection();
			String sql="insert into board(b_title,b_content,b_date,u_id) values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_date());
			pstmt.setString(4, board.getU_id());
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public String getDate(){
		String date=null;
		SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time= new Date();
		date= t.format(time);
		return date;
	}
	
	public ArrayList<Board> getBoards(int page){		
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		ArrayList<Board> list= null;
		int pageNum= (page-1)*3;
		
		try {
			conn= DBConnectionB.getConnection();
			String query= new StringBuilder()
					.append("SELECT     @ROWNUM := @ROWNUM -1 AS ROWNUM,\n")
					.append("           ta.*\n")
					.append("FROM       board ta,\n")
					.append("           (SELECT @rownum := (SELECT COUNT(*)-?+1 FROM board ta))tb\n")
					.append("LIMIT      ?,3\n")
					.toString();
			
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs=pstmt.executeQuery();
			list= new ArrayList<Board>();
			
			while (rs.next()) {
				Board board= new Board();
				
	
				board.setRownum(rs.getInt("ROWNUM"));
				board.setB_num(rs.getInt("b_num"));
				board.setB_title(rs.getString("b_title"));
				board.setB_date(rs.getString("b_date"));
				board.setB_content(rs.getString("b_content"));
				board.setU_id(rs.getString("u_id"));
				board.setB_hits(rs.getInt("b_hits"));
				
				list.add(board);
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
				if(rs != null)rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return list;

	}
	
	
	public void editBoard(Board board) {
		Connection conn= null;
		PreparedStatement pstmt= null;
        try {
			
			conn= DBConnectionB.getConnection();
			String sql="UPDATE board SET b_title=?,b_content=?,b_date=?  where b_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_date());
			///pstmt.setString(4, board.getU_id());
			pstmt.setInt(4, board.getB_num());
	
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void deleteBoard(Board board) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		try {
			conn= DBConnectionB.getConnection();
		    String sql="delete from board where b_num=?";
		    pstmt=conn.prepareStatement(sql);
		    pstmt.setInt(1, board.getB_num());
		    pstmt.executeUpdate();
		
		}catch(Exception e) {
			System.out.println("Ex 틀림:"+e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			    if(conn != null) conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public Board selectBoard(int b_num) {
		  PreparedStatement pstmt= null;
		  ResultSet rs= null;
		  Connection conn=null;
		  Board board= null;
		  try {
			  conn= DBConnectionB.getConnection();
		      String query = "select * from board where b_num = ?";
		      pstmt=conn.prepareStatement(query);
		      pstmt.setInt(1,b_num);
		      board = new Board();

		      rs= pstmt.executeQuery();
		  
		  
		  
		      if(rs.next()){
		    	  board.setB_num(rs.getInt("b_num"));
		    	  board.setB_date(rs.getString("b_date")); 
		    	  board.setB_title(rs.getString("b_title")); 
		    	  board.setB_content(rs.getString("b_content")); 
		    	  board.setB_hits(rs.getInt("b_hits")); 
		    	  board.setU_id(rs.getString("u_id")); 
		      }
		  }catch(Exception e) {
			System.out.println("Ex 틀림:"+e.getMessage());
		  } finally {
			try {
				if(pstmt != null) pstmt.close();
			    if(conn != null) conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		 
		}
			

		
		return board;
		  
	}
	
	
	public void Hits(int b_num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		
		try {
			conn = DBConnectionB.getConnection();
			String sql="UPDATE board SET b_hits= b_hits+1 WHERE b_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("EX오류:"+e.getMessage());
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	
	}
	public void Comment(Board board) {
		PreparedStatement pstmt= null;
		Connection conn= null;
		
		try {
			conn=DBConnection.getConnection();
			String sql="insert into comment(c_comment,u_id,b_num,c_date) values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,board.getC_comment());
			pstmt.setString(2,  board.getC_id());
			pstmt.setInt(3, board.getB_num());
			pstmt.setString(4, board.getB_date());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("Exception 문제:"+e.getMessage());
		}finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
		
				
	}
		
	public ArrayList<Board> getComment(int b_num) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		Connection conn= null;
		ArrayList<Board> list4= null;

		
		
		try {
			conn= DBConnection.getConnection();
			String query="select * from comment where b_num=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, b_num);
			
			rs= pstmt.executeQuery();
		    list4= new ArrayList<Board>();
			while(rs.next()) {
				Board board= new Board();
				board.setB_num(rs.getInt("b_num"));
				board.setC_comment(rs.getString("c_comment"));
				board.setB_date(rs.getString("c_date"));
				board.setC_id(rs.getString("u_id"));
				list4.add(board);
			
			}
		}catch(Exception e) {
			System.out.println("Exception예외임:"+e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null)conn.close();
				if(rs != null)rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		return list4;
		
	}
	
	public int BoardCount() {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int count=0;
		
		
		try {
			conn= DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board";
			pstmt= conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				count= rs.getInt("count");
			}			
		} catch (Exception e) {
			System.out.println("Exception문제:"+e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return count;
	}
	

	
	
	

	

}
