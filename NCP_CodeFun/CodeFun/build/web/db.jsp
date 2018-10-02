<%-- 
    Document   : db
    Created on : 5 Sep, 2018, 7:20:47 PM
    Author     : Suvanika S Rajan
--%>
<%@page session="true"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="org.w3c.dom.*"%>
<%@page import="com.codoid.products.fillo.Fillo"%>
<%@page import="com.codoid.products.fillo.Recordset"%>

<%
     Fillo fillo = new Fillo();
           com.codoid.products.fillo.Connection connection = fillo.getConnection("C:\\Users\\Suvanika S Rajan\\Documents\\GitHub\\NCP_CodeFun\\DB.xls");
		String strQuery = "Select * from Sheet1";
		Recordset recordset = connection.executeQuery(strQuery);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File("C:\\Users\\Suvanika S Rajan\\Documents\\GitHub\\NCP_CodeFun\\add_user.xml"));
             doc.getDocumentElement().normalize();
             System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
             NodeList listOfPersons = doc.getElementsByTagName("User");
             for (int s = 0; s < listOfPersons.getLength(); s++) {
                 Node firstPersonNode = listOfPersons.item(s);
                 if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
                     Element firstPersonElement = (Element) firstPersonNode;
                     NodeList nameList = firstPersonElement.getElementsByTagName("Name");
                     Element nameElement = (Element) nameList.item(0);
                     NodeList textFNList = nameElement.getChildNodes();
                     String name = ((Node) textFNList.item(0)).getNodeValue().trim();
                     NodeList emailList = firstPersonElement.getElementsByTagName("Email");
                     Element emailElement = (Element) emailList.item(0);
                     NodeList textLNList = emailElement.getChildNodes();
                     String email = ((Node) textLNList.item(0)).getNodeValue().trim();
                     
                     NodeList passwordList = firstPersonElement.getElementsByTagName("Password");
                     Element passwordElement = (Element) passwordList.item(0);
                     NodeList passwordLNList = passwordElement.getChildNodes();
                     String password = ((Node) passwordLNList.item(0)).getNodeValue().trim();
                     int flag=0;
                     while (recordset.next()) {
                    System.out.println("name: " + recordset.getField("name") + " email: " + recordset.getField("email"));
                    if(recordset.getField("email").equals(email))
                    {
                        System.out.println("email"+email);
                        flag=1;
                        
                        
                    }
                 }
                     if(flag==0)
                     {
                     int i = connection.executeUpdate("insert into Sheet1(Name,email,password) values('" + name + "','" + email + "','" + password + "')");
                     
                 
                 }
                     else
                     {
                         response.sendRedirect("userexists.html");
                     }
                 }
                 
             }
             
             recordset.close();
             connection.close();
         
     
    
%>
