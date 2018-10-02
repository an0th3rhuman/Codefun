/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import javax.servlet.http.Cookie;

/**
 *
 * @author Suvanika S Rajan
 */
public class dbServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void Request(HttpServletRequest request, HttpServletResponse response, String name, String password)
            throws ServletException, IOException, Exception {

        Fillo fillo = new Fillo();
        com.codoid.products.fillo.Connection connection = fillo.getConnection("C:\\Users\\Suvanika S Rajan\\Documents\\GitHub\\NCP_CodeFun\\DB.xls");
        String strQuery = "Select * from Sheet1";
        Recordset recordset = connection.executeQuery(strQuery);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        int flag = 0;
        while (recordset.next()) {
            System.out.println("PAssword: " + recordset.getField("password") + " email: " + recordset.getField("email"));
            String name_db = recordset.getField("email");
            String password_db = recordset.getField("password");
            if (name_db.equals(name) && password_db.equals(password)) {
                flag = 1;
                System.out.println("heyyy");
                response.sendRedirect("dashboard.html");
            }
        }
        if (flag == 0) {
            response.sendRedirect("badlogin.html");
        }

        recordset.close();
        connection.close();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Cookie firstName = new Cookie("name", request.getParameter("name"));
        Cookie passWord = new Cookie("password", request.getParameter("password"));
        firstName.setMaxAge(60 * 60 * 24);
        response.addCookie(passWord);
        response.addCookie(firstName);
        response.setContentType("text/html");

        try {
            Request(request, response, name, password);
        } catch (Exception ex) {
            Logger.getLogger(dbServlet.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (Exception ex) {
            Logger.getLogger(dbServlet.class.getName()).log(Level.SEVERE, null, ex);
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
