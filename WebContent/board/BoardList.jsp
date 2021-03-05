<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등업신청</title>
</head>
<style>
 h1{
  background-color:#000066;
  text-align:center; 
  color:white;
  width:300px; 
  height:50px;

}
table {
    border-collapse: collapse;
    margin:50px auto;
    
  }
table tr td{
    border:2px solid  #000066;
    text-align:center;
    width:100px;
    height:80px;
  }
  
.td-header {
	height: 10px;
}
p{
  width:50px; 
  height:30px;
  background-color:#000066;
  position: relative;
  left: 970px;
  top: 30px;


}
ul{
     width:400px;
     heigth:50px;
     margin:30px auto;
     }
li{
      list-style:none;
      width:40px;
      line-height:40px;
      border:1px solid #000066;
      float:left;
      text-align:center;
      margin:0 5px;
      border-radius:10px 100px / 120px;
     }


</style>
<body>
<center><h1>등업신청</h1></center>
<p><a href= "user/login-result.jsp" style="color: white">이전</a></p>
<p><a href= "board-insert.do" style="color: white">글쓰기</a></p>
<table>
<tr>
      <td class="td-header">NUM</td>
      <td class="td-header">제목</td>
      <td class="td-header">ID</td>
      <td class="td-header">날짜</td>
      <td class="td-header">조회수</td>
  </tr> 
  

 <c:forEach items="${list2}" var="item" varStatus= "status"> 

 
  <tr>
    <td><a href= "board-listDetail.do?b_num=${item.b_num}">${item.b_num}</a></td>
    <td>${item.b_title}</td>
    <td>${item.u_id}</td>
    <td>${item.b_date}</td>
    <td>${item.b_hits}</td>

  </tr>
 </c:forEach>

</table>
 <div>
     <ul>
       <c:choose>
       <c:when test="${pagination2.prevPage lt 1}">
          <li style= "display:none;">
          <span>◀</span>
          </li>
       </c:when>
       <c:when test= "${pagination2.prevPage ge 1}">
       <li>
         <a href= "board-BoardList.do?page=${pagination2.prevPage}">
         ◀
         </a>
       </li>
       </c:when>
       </c:choose>
       <c:forEach var="i" begin = "${pagination2.startPage}" end = "${pagination2.endPage}" step ="1">
         <c:choose>
         <c:when test="${pagination2.page eq i}">
         <li style= "background-color:#000066;">
           <span>${i}</span>
           </li>
         </c:when>
         <c:when test= "${pagination2.page ne i}">
          <li >
              <a href= "board-BoardList.do?page=${i}">${i}</a>
         
          </li>
         
         
         </c:when>
         
         </c:choose>
       
       </c:forEach>
       
       <c:choose>
       <c:when test= "${pagination2.nextPage lt pagination2.lastPage}">
       <li style="">
           <a href= "board-BoardList.do?page=${pagination2.nextPage}">▶</a>
        </li>
       </c:when>
       <c:when test ="${pagination2.nextPage ge pagination2.lastPage}">
       <li style= "display:none;">
        <a href= "board-BoardList.do?page=${pagination2.nextPage}"></a>
         
       </li>
       </c:when>
       </c:choose>
        <%-- <li>
           <a href= "board-BoardList.do?page=${pagination2.nextPage}">▶</a>     
           </li> --%>
       
       
       
     </ul>
   
   
   </div>



</body>
</html>