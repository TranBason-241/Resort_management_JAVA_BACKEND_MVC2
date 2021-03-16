/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RoomDAO;
import dto.CartDTO;
import dto.RoomDetailDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(CheckOutController.class);
    private static final String ERROR = "cart.jsp";
    private static final String CART = "cart.jsp";

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

            HttpSession ss = request.getSession();
            CartDTO cart = (CartDTO) ss.getAttribute("CART");
            float total = 0;
            if (cart != null) {
                for (RoomDetailDTO c : cart.getCart().values()) {
                    total += c.getPrice();
                }
            }
            
            total += (total / 100) * 10;
            int max = 999;
            int min = 1;
            Random random = new Random();
            int randomID = random.nextInt(max - min) + min;

            UserDTO user = (UserDTO) ss.getAttribute("USER");

            RoomDAO dao = new RoomDAO();

         
            
            int rs = dao.checkOut(user.getUserID(), cart, total, randomID);
            
            if(rs==1){
                url = CART;
                request.setAttribute("SUCCESS", "Order Success");
                ss.setAttribute("CART", null);
            }
            
        } catch (SQLException e) {
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
