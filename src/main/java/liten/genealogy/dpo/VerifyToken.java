/**
 *
 * @author FACULTY
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.util.*;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@Named
@RequestScoped
@XmlRootElement(name = "API3G")
@XmlAccessorType(XmlAccessType.FIELD)
public class VerifyToken implements Serializable {

    @XmlElement
    String CompanyToken = "9F416C11-127B-4DE2-AC7F-D5710E4C5E0A";
    @XmlElement
    String Request = "verifyToken";
    @XmlElement
    String TransactionToken = "";

    public VerifyToken() {
    }

    public void setCompanyToken(String CompanyToken) {
        this.CompanyToken = CompanyToken;
    }

    public String getCompanyToken() {
        return this.CompanyToken;
    }

    public void setRequest(String Request) {
        this.Request = Request;
    }

    public String getRequest() {
        return this.Request;
    }

    public void setTransactionToken(String transactionToken) {
        this.TransactionToken = transactionToken;
    }

    public String getTransactionToken() {
        return this.TransactionToken;
    }

    public static String convertDate(Date date) {
        Format format = new SimpleDateFormat("YYYY/MM/DD HH:MM");
        return format.format(date);
    }

}
