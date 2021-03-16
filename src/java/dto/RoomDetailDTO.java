/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;



/**
 *
 * @author ADMIN
 */
public class RoomDetailDTO {
    String roomID;
    String roomName;    
    Float price;  
    boolean status;
    Date checkIn;
    Date checkOut;
    int dateNumber;

    public RoomDetailDTO(String roomID, String roomName, Float price, boolean status, Date checkIn, Date checkOut, int dateNumber) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.price = price;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.dateNumber = dateNumber;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(int dateNumber) {
        this.dateNumber = dateNumber;
    }

   
    
}
