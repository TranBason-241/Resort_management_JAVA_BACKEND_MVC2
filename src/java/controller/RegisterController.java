/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.ErrorDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);
private static final String ERROR = "register.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url = ERROR;
       
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String re_pass = request.getParameter("re_pass");
            
            ErrorDTO error =new ErrorDTO();
            boolean flag = true;
            if(id.trim().isEmpty()){
                flag = false;
                error.setIdError("Please input userID");
                
            }
            if(name.trim().isEmpty()){
                flag = false;
                error.setNameError("please input full name");
            }
            if(email.trim().isEmpty()){
                flag = false;
                error.setEmailEror("please in put email");
            }
            if(pass.trim().isEmpty()){
                flag = false;
                error.setPass1("can not empty");
            }
            if(re_pass.trim().isEmpty()){
                flag = false;
                error.setPass2("can not empty");
            }
            if(flag){
                if(id.length() > 8 || id.length() <5){
                    error.setIdError("id's lenght [5-8]");
                    url = ERROR;
                }
                if(name.length() > 16 || name.length() <8){
                    error.setNameError("name's lenght [8-16]");
                    url = ERROR;
                }
                if(email.length() > 50 || email.length() <10){
                    error.setEmailEror("Email is invalid");
                    url = ERROR;
                }
                if(!pass.equals(re_pass)){
                    error.setPass1("pass word not same");
                    url = ERROR;
                }
            }
            if(!flag){
                url = ERROR;
            }
            
            request.setAttribute("ERROR", error);
            int check = 0;
            
            UserDAO dao = new UserDAO();
            try {
                check = dao.createUser(id, name, email, pass);
            } catch (SQLException e) {
                if(e.toString().contains("duplicate key in object")){
                    error.setIdError("id already exists ");
                }
            }
            if(check == 1){
                request.setAttribute("success", "created successfully, login now");
            }
            
            
            
            
        } catch (Exception e) {
            LOGGER.error("error: ", e);
           
        }finally{
             request.getRequestDispatcher(url).forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
