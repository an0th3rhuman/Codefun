package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.sql.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public final class db_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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
                     
                   Cookie Name = new Cookie("name", email);
        
        Name.setMaxAge(60 * 60 * 24);

        response.addCookie(Name);
                 }
                     else
                     {
                         response.sendRedirect("userexists.html");
                     }
                 }
                 
             }
             
             recordset.close();
             connection.close();
         
     
    

      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
