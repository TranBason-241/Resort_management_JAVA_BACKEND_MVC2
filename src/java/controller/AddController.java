/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RoomDAO;
import dto.CartDTO;
import dto.RoomDTO;
import dto.RoomDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
    private static final String ERROR = "invalid2.jsp";
    private static final String SEARCH = "search.jsp";

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
            String customer = (String) ss.getAttribute("LOGIN_NAME");
            CartDTO cart = (CartDTO) ss.getAttribute("CART");
            String checkIn = (String) ss.getAttribute("depart");
            String checkOut = (String) ss.getAttribute("returnn");

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkIn);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkOut);

            long deff = endDate.getTime() - startDate.getTime();

            long getDaysDiff = TimeUnit.MILLISECONDS.toDays(deff);
            int a = (int) getDaysDiff;

            if (cart == null) {
                cart = new CartDTO(customer, null);
            }

            String roomID = request.getParameter("roomID");
            
            if (roomID != null) {
                RoomDAO dao = new RoomDAO();
                ArrayList<RoomDTO> list = dao.getRoom("", "");
                for (RoomDTO r : list) {
                    if (r.getRoomID().equals(roomID)) {
                        RoomDetailDTO room = new RoomDetailDTO(r.getRoomID(), r.getRoomName(), r.getPrice(), r.isStatus(), startDate, endDate, a);
                        cart.add(room);
                    }

                    url = SEARCH;
                }
            }
//            HashMap<String,RoomDTO> a = cart.getCart();
//            ArrayList<RoomDTO> b = a.values();
            ss.setAttribute("CART", cart);
        } catch (SQLException | ParseException e) {
            LOGGER.error("error: ", e);
        } finally {
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
