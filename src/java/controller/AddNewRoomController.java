/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RoomDAO;
import dto.ErrorDTO;
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
@WebServlet(name = "AddNewRoomController", urlPatterns = {"/AddNewRoomController"})
public class AddNewRoomController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddNewRoomController.class);
    private static final String ERROR = "addNewRoom.jsp";
    private static final String SUCCESS = "ManageController";

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
            String id = request.getParameter("txtRoomID");

            RoomDAO dao = new RoomDAO();
            ArrayList<RoomDTO> list = dao.getRoom("", "");
            boolean check = true;
            for (RoomDTO r : list) {
                if (r.getRoomID().equals(id)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                String name = request.getParameter("txtRoomName");
                float price = Float.parseFloat(request.getParameter("txtPrice"));
                String description = request.getParameter("txtDescription");
                String img = "images" + request.getParameter("link");

                RoomDTO room = new RoomDTO(id, name, description, price, img, false);

                int rs = dao.createRoom(room);
                if (rs == 1) {
                    url = SUCCESS;
                }
            }else{
                url = ERROR;
                ErrorDTO error = new ErrorDTO();
                error.setIdError("Id already exits");
                request.setAttribute("ERROR", error);
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
