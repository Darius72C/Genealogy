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
@XmlRootElement(name = "API3G")
@XmlAccessorType(XmlAccessType.FIELD)
public class DPOResponse implements Serializable {
    @XmlElement
    private String Result;
        @XmlElement
    private String ResultExplanation;
            @XmlElement
    private String TransToken;
                @XmlElement
    private String TransRef;
                    @XmlElement
    private String Notes;
                        @XmlElement
    private String AllocationID;
                            @XmlElement
    private String AllocationCode;

    public DPOResponse() {

    }

    public void setResult(String result) {
        this.Result = result;
    }

    public String getResult() {
        return this.Result;
    }

    public void setResultExplanation(String resultExplanation) {
        this.ResultExplanation = resultExplanation;
    }

    public String getResultExplanation() {
        return this.ResultExplanation;
    }

    public void setTransToken(String transToken) {
        this.TransToken = transToken;
    }

    public String getTransToken() {
        return this.TransToken;
    }

    public void setTransRef(String transRef) {
        this.TransRef = transRef;
    }

    public String getTransRef() {
        return this.TransRef;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setAllocationID(String allocationID) {
        this.AllocationID = allocationID;
    }

    public String getAllocationID() {
        return this.AllocationID;
    }

    public void setAllocationCode(String allocationCode) {
        this.AllocationCode = allocationCode;
    }

    public String getAllocationCode() {
        return this.AllocationCode;
    }
}
