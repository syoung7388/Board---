<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<style>
 h2{
  background-color:#9696FF;
  text-align:center; 
  color:white;
  width:300px; 
  height:50px;
  margin:50px auto;
  border-radius:10px 100px / 120px

}

table {
    border-collapse: seperate;
    margin-left: 90px;
  }

table tr td{
    border-bottom: 1px solid  #7878ff;
    text-align:left;
    width:1000px;
}


#btn_group button{ 
  background-color: #7878ff;
  padding:5px;
  border: 1px solid #7878ff;
  margin-left: 90px;
  }
p{
  margin-left: 90px;
  font-size:10px;

}
</style>



<body>

<center>
<h2>게시물 확인</h2>
</center>
<table>

  
  <tr >
  <td style="border:0 solid black; height:50px; font-size:40px;">${board.b_title}</td>
  </tr>
  
  <tr>
  <td style="border:0 solid black; height:20px;font-size:10px;" >${board.u_id}님</td>
  </tr>
   <tr>
  <td style="border:0 solid black; height:10px;font-size:10px;">${board.b_date}</td>
  </tr>
  
   <tr>
  <td style="height:20px;font-size:10px; width:100px">${board.b_hits}</td>
  </tr>
  
  <tr>
  <td style="height:200px; width:140px" >${board.b_content}</td>
  </tr>
  

</table>
<div id="btn_group"> 
<button><a href="board-BoardList.do?b_num= ${board.b_num}"  style="color: white" >이전</a></button>

<c:set var="c_id" value="${sessionScope.user.u_id}" />
<c:set var="b_id" value="${board.u_id}" />

<c:choose>

   <c:when test="${c_id eq b_id}">
      
      <button><a href="board-BoardEdit.do?b_num=${board.b_num}"  style="color: white">수정</a></button>
      <button><a href="board-BoardDelete.do?b_num=${board.b_num}"  style="color: white">삭제</a></button>
      </div>
    </c:when>

    <c:when test="${c_id ne b_id}">
      <div style= "display:none;">
        <button>수정</button>
        <button>삭제</button></div>
     
    </c:when>
</c:choose>

<h1 style="font-size:20px; margin-left: 90px;">댓글</h1>

<div id="div_comment">
	<c:forEach items="${list4}" var="item" varStatus= "status">
		<p>${item.c_id}님->${item.c_comment}(${item.b_date})</p>
	</c:forEach>
</div>
 
 
 <form action ="board-comment-process.do" name="user" method= "post"> 
     <p><input type= "text" name="c_comment" ></p>
     <!-- p><input type="submit" value="등록하기"></p-->
     <p><input type="button" value="등록하기" id="btn_ok"></p>
     <p><input type="hidden" name="b_num" value="${board.b_num}"> </p>
     <p><input type="hidden" name="c_id" value="${sessionScope.user.u_id}"> </p>
</form>





<script>
$(document).on('click', '#btn_ok', function (){
	let cComment = $('input[name="c_comment"]').val();
	let cId = '${sessionScope.user.u_id}';
	
	$.ajax({
	  method: "POST",
	  url: "/JSP/board-comment-process.do",
	  data: { c_comment: cComment, c_id: cId, b_num: '${board.b_num}'}
	})
	.done(function( html ) {
	  $('#div_comment').html(html);
	});
});
</script>
</body>
</html>