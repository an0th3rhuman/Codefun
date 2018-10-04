/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Suvanika S Rajan
 */
public class jdbc extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            System.out.println("heyyy");
//step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object  
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "suvi", "starfire");
            Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = request.getCookies();
            String name = "";
            String Q = "";
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];

                if ((cookie.getName()).compareTo("email") == 0) {
                    name = cookie.getValue();
                }
                if ((cookie.getName()).compareTo("Q") == 0) {
                    Q = cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }

            }
            System.out.println("name: " + name);

//step3 create the statement object  
            Statement stmt = con.createStatement();
            String query = "";
            if ("1".equals(Q)) {
                query = "update STATUS set Q1= 25 where NAME=" + "'" + name + "'";
            }

            if ("2".equals(Q)) {
                query = "update STATUS set Q2=25 where NAME=" + "'" + name + "'";
            }
            //count will give you how many records got updated
            System.out.print(query);
            int count = stmt.executeUpdate(query);
            System.out.println("Updated queries: " + count);
            if (count >= 1) {
                
                System.out.println("name: " + name);
                int progress = 0;
//step3 create the statement object  
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery("select * from status where name=" + "'" + name + "'");
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getInt("Q1"));
                    progress += rs.getInt("Q1");
                    progress += rs.getInt("Q2");
                }
                System.out.println("progress: " + progress);
                Cookie prog = new Cookie("progress", Integer.toString(progress));
                response.addCookie(prog);
                rs.close();
                con.close();

              

            }
            response.sendRedirect("progressbar.html");

//step5 close the connection object  
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

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
