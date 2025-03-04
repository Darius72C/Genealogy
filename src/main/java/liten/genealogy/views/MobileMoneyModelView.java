/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.MobileOperator;
import liten.genealogy.mainEntities.MobilePhone;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class MobileMoneyModelView implements Serializable {
    String mobileOperatorID;
    String mobilePhoneID;
    MobileOperator mobileOperator;
    MobilePhone mobilePhone;
    List<MobileOperator> mobileOperatorList;
    List<MobilePhone> mobilePhoneList;

    public MobileMoneyModelView() {
    }

    public String getMobileOperatorID() {
        return mobileOperatorID;
    }

    public void setMobileOperatorID(String mobileOperatorID) {
        this.mobileOperatorID = mobileOperatorID;
    }

    public String getMobilePhoneID() {
        return mobilePhoneID;
    }

    public void setMobilePhoneID(String mobilePhoneID) {
        this.mobilePhoneID = mobilePhoneID;
    }

    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public List<MobilePhone> getMobilePhoneList() {
        return mobilePhoneList;
    }

    public void setMobilePhoneList(List<MobilePhone> mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
    }
    
    
}
