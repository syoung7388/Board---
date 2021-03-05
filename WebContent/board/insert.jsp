<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>글쓰기 작성</title>
</head>
<style>
  h2{
  background-color:#000066;
  text-align:center; 
  color:white;
  width:300px; 
  height:50px;

}
  table {
    border-collapse: separate;
    margin:50px auto;
    
  }
  table tr td{
    border:2px solid  #000066;
    text-align:center;
  }




  </style>

<body>
	<form action="board-insert-process.do" name="board" method="post" >
	<input type="hidden" name="u_id" value="${sessionScope.user.u_id }">
	
	
	
	
	<center> 
	<h2> 글쓰기</h2> 
	</center>
    <table>
	

  <tr>
    <td>제목</td>
    <td><input type="text"style="border:0 solid black;width:500px; height:20px" name="title"></td>
  </tr>

  <tr>
    <td>내용</td>
    <td style= "width:500px; height:100px"><textarea name="content" style ="border:0 solid black" cols="100" rows="30"></textarea></td>
  </tr>
   <tr >
   <td colspan=2 style="text-align:center;border:0 solid black;width:500px">
     <input type="submit" value="등록하기">
     </td>
     </tr>
</table>

</form>

</body>
</html>