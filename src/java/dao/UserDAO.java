/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import util.MyConnection;

/**
 *
 * @author ADMIN
 */
public class UserDAO {
private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    public UserDTO checkUser(String userID, String password) throws SQLException {
        Connection cn = null;
        ResultSet rs = null;
        UserDTO user = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT userID,userName,roleID,email,password\n"
                        + "FROM dbo.tblUser\n"
                        + "WHERE password = '" + password + "' AND userID ='" + userID + "'";
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String ID = rs.getString("userID");
                    String userName = rs.getString("userName");
                    String roleID = rs.getString("roleID");
                    String ps = rs.getString("password");
                    String email = rs.getString("email");

                    user = new UserDTO(ID, userName, roleID, ps, email);
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
        return user;
    }

    public int createUser(String userID, String Name, String email, String password) throws SQLException {
        Connection cn = null;
        int rs = 10;
       
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "INSERT dbo.tblUser\n"
                        + "        ( userID ,\n"
                        + "          userName ,\n"
                        + "          roleID ,\n"
                        + "        \n"
                        + "          password ,\n"
                        + "          email\n"
                        + "        )\n"
                        + "VALUES  ( N'"+userID+"' , -- userID - nvarchar(50)\n"
                        + "          N'"+Name+"' , -- userName - nvarchar(30)\n"
                        + "          N'US' , -- roleID - nvarchar(5)\n"
                        + "        \n"
                        + "          N'"+password+"' , -- password - nvarchar(50)\n"
                        + "          N'"+email+"'  -- email - nvarchar(50)\n"
                        + "        )";
                pst = cn.prepareCall(sql);
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
//    public static void main(String[] args) throws SQLException {
//        UserDAO a = new UserDAO();
//        a.createUser("", "", "", "");
//       
//    }
}
