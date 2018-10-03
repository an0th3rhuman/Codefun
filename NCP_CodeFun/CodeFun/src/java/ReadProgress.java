/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suvanika S Rajan
 */
@WebServlet(urlPatterns = {"/ReadProgress"})
public class ReadProgress extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception  {
        response.setContentType("text/html;charset=UTF-8");
       try{  
           System.out.println("heyyy");
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","suvi","starfire");  
   Cookie cookie = null;
      Cookie[] cookies = null;
       cookies = request.getCookies();
       String name="";
       String Q="";
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];

            if((cookie.getName( )).compareTo("email") == 0 ) {
                 name = cookie.getValue();
            }
                   
            
        
            }
        System.out.println("name: "+name);
           int progress=0;
//step3 create the statement object  
Statement stmt=con.createStatement();  
ResultSet rs = stmt.executeQuery("select * from status where name="+"'"+name+"'" );
            while(rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("Q1"));
                progress+=rs.getInt("Q1");
                progress+=rs.getInt("Q2");
            }
            System.out.println("progress: "+progress);
             Cookie prog = new Cookie("progress", Integer.toString(progress));
             response.addCookie(prog);
             rs.close();
            con.close();

             response.sendRedirect("home.html"); 
            

//step5 close the connection object  
con.close();  
  
}catch(Exception e){ System.out.println(e);}  
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
      }

      
         
     }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
