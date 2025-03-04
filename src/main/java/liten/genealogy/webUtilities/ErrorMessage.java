/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 *
 * @author FACULTY
 */
@Named
@RequestScoped
public class ErrorMessage implements Serializable {

    private int code;
    private String message;

    public ErrorMessage() {

    }

    public ErrorMessage(int code) {
        code = this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
