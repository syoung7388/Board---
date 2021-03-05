<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정값 저장하기</title>
</head>
<body>
<%@ include file = "db_connection.jsp" %>
<%
   request.setCharacterEncoding("UTF-8");
   String idx = request.getParameter("u_idx");
   String id = request.getParameter("u_id");
   String password = request.getParameter("u_password");
   String name = request.getParameter("u_name");
   String tel1 = request.getParameter("u_tel1");
   String tel2 = request.getParameter("u_tel2");
   String tel3 = request.getParameter("u_tel3");
   String tel= tel1+"-"+tel2+"-"+tel3;
   String age= request.getParameter("u_age");
   
   PreparedStatement pstmt= null;
   
   try{
	   
	   String sql= "UPDATE user SET u_id=?,u_pw=?, u_name=?,u_tel=?,u_age=? where u_idx=?";
	   pstmt= conn.prepareStatement(sql);
	   
	   pstmt.setString(1,id);
	   pstmt.setString(2,password);
	   pstmt.setString(3,name);
	   pstmt.setString(4,tel);
	   pstmt.setString(5,age);
	   pstmt.setString(6,idx);
	   pstmt.executeUpdate();
%>
     <h3>수정완료</h3>
<%
   }catch(SQLException ex){
	   System.out.println("SQLException->"+ex.getMessage());
   } finally {
	   if(pstmt != null){
		   pstmt.close();
	   }
	   if(conn != null){
		   conn.close();
	   }
   }
   %>
<a href="userList.jsp">돌아가기</a>
</body>
</html>