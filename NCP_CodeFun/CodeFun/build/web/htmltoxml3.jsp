<%-- 
    Document   : htmltoxml3
    Created on : 6 Sep, 2018, 9:44:03 AM
    Author     : Suvanika S Rajan
--%>

<%-- 
    Document   : htmltoxml
    Created on : 5 Sep, 2018, 7:16:23 PM
    Author     : Suvanika S Rajan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.w3c.dom.DocumentType"%>
<%@page import="org.w3c.dom.DOMImplementation"%>
<%@page import="java.io.File"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.transform.OutputKeys"%>
<%@page import="javax.xml.transform.Transformer"%>
<%@page import="javax.xml.transform.TransformerFactory"%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="javax.xml.transform.stream.StreamResult"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.Node"%>
<%
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();
    
    //add elements to Document
    Element Profile = doc.createElement("Users");
    doc.appendChild(Profile);
    Element Users = doc.createElement("UserInfo");
    Profile.appendChild(Users);
    Element Name = doc.createElement("Name");
    Name.appendChild(doc.createTextNode(request.getParameter("name")));
    Element Email = doc.createElement("Email");
   
    Email.appendChild(doc.createTextNode(request.getParameter("email")));
    Element Subject= doc.createElement("Subject");
    Subject.appendChild(doc.createTextNode(request.getParameter("subject")));
     Element Message = doc.createElement("Message");
    Message.appendChild(doc.createTextNode(request.getParameter("message")));
     System.out.println("name"+ Message);
     System.out.println("name"+request.getParameter("message"));
    Users.appendChild(Name);
    Users.appendChild(Email);
    Users.appendChild(Subject);
 Users.appendChild(Message);
    //for output to file, console
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    //for pretty print
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    DOMImplementation domImpl = doc.getImplementation();
    DocumentType doctype = domImpl.createDocumentType("doctype","-//Oberon//YOUR PUBLIC DOCTYPE//EN","userinfo.dtd");
    //transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
    
    DOMSource source = new DOMSource(doc);
    //write to console or file
	StreamResult file = new StreamResult(new File("C:\\Users\\Suvanika S Rajan\\Documents\\GitHub\\NCP_CodeFun\\userinfo.xml"));
    //write data
    transformer.transform(source, file);
    response.sendRedirect("db3.jsp");
    
%>


