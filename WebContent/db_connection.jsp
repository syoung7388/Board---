<%@ page import="java.sql.*" %>

<%
   Connection conn = null;
   String url = "jdbc:mysql://localhost:3306/test";
   String user= "root";
   String dbPassword= "4848";
   
   Class.forName("org.mariadb.jdbc.Driver");
   conn= DriverManager.getConnection(url, user, dbPassword);

%>