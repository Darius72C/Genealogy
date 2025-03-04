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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@XmlRootElement(name = "API3G")
@XmlAccessorType(XmlAccessType.FIELD)
public class DPORequest implements Serializable {

    @XmlElement
    String CompanyToken = "73AED819-E6D9-4B34-AFE3-852F905AB6A5";
            //"9F416C11-127B-4DE2-AC7F-D5710E4C5E0A";
    @XmlElement
    String Request = "createToken";
    @XmlElement(name = "Transaction")
    Transaction Transaction;
    @XmlElementWrapper(name = "Services")
    @XmlElement(name = "Service")
    List<Service> Services;
    //        @XmlElementWrapper(name="Allocations")
//@XmlElement(name="Allocation")
    // List<Allocation> Allocations;

    public DPORequest() {
        Transaction = new Transaction();
        Services = new ArrayList<Service>();
        //   Allocations = new ArrayList<Allocation>();
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

    public void setTransaction(Transaction transaction) {
        this.Transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.Transaction;
    }

    public void setServices(List<Service> services) {
        this.Services = services;
    }

    public List<Service> getServices() {
        return this.Services;
    }

    /*   public void setAllocations(List<Allocation> allocations) {
        this.Allocations = allocations;
    }

    public List<Allocation> getAllocations() {
        return this.Allocations;
    }*/
    
    
    public static String convertDate(Date date) {
        Format format = new SimpleDateFormat("YYYY/MM/DD HH:MM");
        return format.format(date);
    }

}
