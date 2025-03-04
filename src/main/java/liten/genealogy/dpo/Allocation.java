/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@XmlRootElement(name = "Allocation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Allocation implements Serializable {

    @XmlElement
    String AllocationCode;
    @XmlElement
    Double AllocationAmount;
    @XmlElement
    String AllocationServiceDescription;
    @XmlElement
    String AllocationServiceType;
    @XmlElement
    String AllocationInvoice;
    @XmlElement
    String AllocationPnr;

    public Allocation() {

    }

    public void setAllocationCode(String AllocationCode) {
        this.AllocationCode = AllocationCode;
    }

    public String getAllocationCode() {
        return this.AllocationCode;
    }

    public void setAllocationServiceDescription(String AllocationServiceDescription) {
        this.AllocationServiceDescription = AllocationServiceDescription;
    }

    public String getAllocationServiceDescription() {
        return this.AllocationServiceDescription;
    }

    public void setAllocationServiceType(String AllocationServiceType) {
        this.AllocationServiceType = AllocationServiceType;
    }

    public String getAllocationServiceType() {
        return this.AllocationServiceType;
    }

    public void setAllocationAmount(Double AllocationAmount) {
        this.AllocationAmount = AllocationAmount;
    }

    public Double getAllocationAmount() {
        return this.AllocationAmount;
    }

    public void setAllocationInvoice(String AllocationInvoice) {
        this.AllocationInvoice = AllocationInvoice;
    }

    public String getAllocationInvoice() {
        return this.AllocationInvoice;
    }

    public void setAllocationPnr(String AllocationPnr) {
        this.AllocationPnr = AllocationPnr;
    }

    public String getAllocationPnr() {
        return this.AllocationPnr;
    }
}
