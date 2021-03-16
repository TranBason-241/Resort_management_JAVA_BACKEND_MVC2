/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RoomDAO;
import dto.RoomDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ManagerUpdateController", urlPatterns = {"/ManagerUpdateController"})
public class ManagerUpdateController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(ManagerUpdateController.class);
    private static final String ERROR = "invalid1.jsp";
    private static final String MANAGE = "ManageController";

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
            String id = request.getParameter("txtID");

            String name = request.getParameter("txtName");
            String des = request.getParameter("txtDescription");
            Float price = Float.parseFloat(request.getParameter("txtPrice"));
            RoomDAO dao = new RoomDAO();
            ArrayList<RoomDTO> list = (ArrayList<RoomDTO>) dao.getRoom2("", "");
            if (list != null) {
                for (RoomDTO r : list) {
                    if (r.getRoomID().equals(id)) {
                        RoomDTO room = new RoomDTO(id, name, des, price, r.getImage(), r.isStatus());
                        int i = dao.UpdateRoom(room);
                        if (i == 1) {
                            url = MANAGE;
                        }

                    }
                }
            }

        } catch (NumberFormatException | SQLException e) {
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
