/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.util.Date;
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
public class CreateToken implements Serializable {

    @XmlElement
    String CompanyToken = "9F416C11-127B-4DE2-AC7F-D5710E4C5E0A";
    @XmlElement
    String Request;

    public CreateToken() {
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

}
