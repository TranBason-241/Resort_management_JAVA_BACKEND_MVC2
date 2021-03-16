/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);
private static final String ERROR = "invalid1.jsp";
private static final String LOGIN = "LoginController";
private static final String VIEW = "cart.jsp";
private static final String SEARCH = "SearchController";
private static final String SIGNUP = "register.jsp";
private static final String CREATEUSER = "RegisterController";
private static final String ADD = "AddController";
private static final String DELETE = "DeleteController";
private static final String LOGOUT = "LogoutController";
private static final String MANAGERDELETE = "ManagerDeleteController";
private static final String MANAGERUPDATE = "ManagerUpdateController";
private static final String BACKTOLOGIN = "login.jsp";
private static final String CHECKOUT = "CheckOutController";
private static final String ADDNEWPAGE = "addNewRoom.jsp";
private static final String ADDNEWROOM = "AddNewRoomController";
private static final String LOGINFIRST = "login.jsp";
private static final String BACKTOMANAGE = "ManageController";


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
            String action = request.getParameter("btnAction");
            if(action.equals("login")){
                url = LOGIN;
            }else if(action.equals("view")){
                url = VIEW;
            }else if(action.equals("search")){
                url = SEARCH;
            }else if(action.equals("signUp")){
                url = SIGNUP;
            }else if(action.equals("add")){
                url = ADD;
            }else if(action.equals("back")){
                url = SEARCH;
            }else if(action.equals("delete")){
                url = DELETE;
            }else if(action.equals("logout")){
                url=LOGOUT;
            }else if(action.equals("managerDelete")){
                url=MANAGERDELETE;
            }else if(action.equals("managerUpdate")){
                url=MANAGERUPDATE;
            }else if(action.equals("register")){
                url=CREATEUSER;
            }else if(action.equals("backToLogin")){
                url=BACKTOLOGIN;
            }else if(action.equals("checkOut")){
                url = CHECKOUT;
            }else if(action.equals("addnew")){
                url = ADDNEWPAGE;
            }else if(action.equals("ADDNEWROOM")){
                url = ADDNEWROOM;
            }else if(action.equals("LoginFirst")){
                url = LOGINFIRST;
            }else if(action.equals("backtomanage")){
                url = BACKTOMANAGE;
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
