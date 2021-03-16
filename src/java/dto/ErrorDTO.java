/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class ErrorDTO {

    String nameError;
    String idError;
    String emailEror;
    String pass1;
    String pass2;
    String pass3;

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getEmailEror() {
        return emailEror;
    }

    public void setEmailEror(String emailEror) {
        this.emailEror = emailEror;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getPass3() {
        return pass3;
    }

    public void setPass3(String pass3) {
        this.pass3 = pass3;
    }

    public ErrorDTO(String nameError, String idError, String emailEror, String pass1, String pass2, String pass3) {
        this.nameError = nameError;
        this.idError = idError;
        this.emailEror = emailEror;
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.pass3 = pass3;
    }

    public ErrorDTO() {

    }

}
