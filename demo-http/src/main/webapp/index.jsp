<%@page import="java.lang.*" %>
<%@page import="java.io.*" %>
<%@page import="com.xhh.demo.http.load.*" %>
<%
    InputStream is = new FileInputStream("/home/tiger/TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();
    System.out.println("<textarea style='width:1000;height=800'>");
    System.out.println(JavaClassExecuter.execute(b));
    System.out.println("</textarea>");
%>