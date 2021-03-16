/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CartDTO;
import dto.RoomDTO;
import dto.RoomDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import util.MyConnection;

/**
 *
 * @author ADMIN
 */
public class RoomDAO {
private static final Logger LOGGER = Logger.getLogger(RoomDAO.class);

    public ArrayList<RoomDTO> getRoom(String depart, String returnn) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<RoomDTO> list = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select  roomID ,roomName, description, price,image,status\n"
                        + "from tblRooms\n"
                        + "where status = 0 and roomID not in (\n"
                        + "select roomID\n"
                        + "from orderDetail\n"
                        + "where  not (depart > '" + returnn + "' or [return] < '" + depart + "'))";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String roomName = rs.getString("roomName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    RoomDTO room = new RoomDTO(roomID, roomName, description, price, image, status);
                    list.add(room);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public ArrayList<RoomDTO> getRoom2(String depart, String returnn) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<RoomDTO> list = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select roomID,roomName,description,price,image,status\n"
                        + "from dbo.tblRooms\n"
                        + "where roomID not in (\n"
                        + "select distinct roomID\n"
                        + "from dbo.orderDetail \n"
                        + "where GETDATE() BETWEEN '" + depart + "' and '" + returnn + "')";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String roomName = rs.getString("roomName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    RoomDTO room = new RoomDTO(roomID, roomName, description, price, image, status);
                    list.add(room);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public int DeleteRoom(String roomID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -10;
        

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.tblRooms SET status ='1' WHERE roomID = '" + roomID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error: ", e);
        } finally {

            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public int UpdateRoom(RoomDTO room) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -10;
       

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.tblRooms SET roomName='" + room.getRoomName() + "',description='" + room.getDescription() + "',price=" + room.getPrice() + " WHERE roomID = '" + room.getRoomID() + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error: ", e);
        } finally {

            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public int checkOut(String userID, CartDTO cart, float total, int randomID) throws SQLException {

        PreparedStatement pst = null;
        int rs = 10;
        Connection cn = null;
        try {
            cn = MyConnection.getConnection();

            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT dbo.tblOrder\n"
                        + "        ( orderID, userID, total, date )\n"
                        + "VALUES  ( '" + randomID + "', -- orderID - char(10)\n"
                        + "          N'" + userID + "', -- userID - nvarchar(50)\n"
                        + "          " + total + ", -- total - float\n"
                        + "          GETDATE()  -- date - datetime\n"
                        + "          )";
                pst = cn.prepareStatement(sql);
                pst.executeUpdate();
                ///
                for (RoomDetailDTO room : cart.getCart().values()) {
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                    String ci = sim.format(room.getCheckIn());
                    String co = sim.format(room.getCheckOut());
                    sql = "INSERT dbo.orderDetail\n"
                            + "        ( oderID ,\n"
                            + "          roomID ,\n"
                            + "          price ,\n"
                            + "          depart ,\n"
                            + "          [return]\n"
                            + "        )\n"
                            + "VALUES  ( N'" + randomID + "' , -- oderID - nvarchar(10)\n"
                            + "          N'" + room.getRoomID() + "' , -- roomID - nvarchar(50)\n"
                            + "          " + room.getPrice() + " , -- price - float\n"
                            + "          '" + ci + "' , -- depart - date\n"
                            + "          '" + co + "'  -- return - date\n"
                            + ")";

                    pst = cn.prepareStatement(sql);
                    rs = pst.executeUpdate();
                }

            }
            cn.commit();

        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error: ", e);
            cn.rollback();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public int createRoom(RoomDTO room) throws SQLException {
        Connection cn = null;
        int rs = 10;
        PreparedStatement pst = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "INSERT dbo.tblRooms\n"
                        + "        ( roomID ,\n"
                        + "          roomName ,\n"
                        + "          description ,\n"
                        + "          price ,\n"
                        + "          image ,\n"
                        + "          status\n"
                        + "        )\n"
                        + "VALUES  ( N'" + room.getRoomID() + "' , -- roomID - nvarchar(50)\n"
                        + "          N'" + room.getRoomName() + "' , -- roomName - nvarchar(50)\n"
                        + "          N'" + room.getDescription() + "' , -- description - nvarchar(1000)\n"
                        + "          " + room.getPrice() + " , -- price - float\n"
                        + "          '" + room.getImage() + "' , -- image - varchar(500)\n"
                        + "          '" + room.isStatus() + "'  -- status - bit\n"
                        + "        )";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }
//    public static void main(String[] args) throws SQLException {
}
