/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public class CartDTO {

    String customer;
    HashMap<String, RoomDetailDTO> cart;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public HashMap<String, RoomDetailDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, RoomDetailDTO> cart) {
        this.cart = cart;
    }

    public CartDTO(String customer, HashMap<String, RoomDetailDTO> cart) {
        this.customer = customer;
        this.cart = cart;
    }

    public void add(RoomDetailDTO room) {
        if (cart == null) {
            cart = new HashMap<>();
        }

        cart.put(room.getRoomID(), room);
    }

    public void delele(String roomID) {
        if (cart == null) {
            return;
        }
        if (cart.containsKey(roomID)) {
            cart.remove(roomID);
        }
    }

}
