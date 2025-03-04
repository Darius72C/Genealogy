/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.AdminFatherTree;
import liten.genealogy.mainEntities.AdminFatherTreePK;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class AdminFatherTreeModelView implements Serializable {
    String adminID;
    String fatherID;
    AdminFatherTree adminMFT;
    List<AdminFatherTree> AdminFatherTreeList;

    public AdminFatherTreeModelView() {
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public AdminFatherTree getAdminMFT() {
        return adminMFT;
    }

    public void setAdminMFT(AdminFatherTree adminMFT) {
        this.adminMFT = adminMFT;
    }

    public List<AdminFatherTree> getAdminFatherTreeList() {
        return AdminFatherTreeList;
    }

    public void setAdminFatherTreeList(List<AdminFatherTree> AdminFatherTreeList) {
        this.AdminFatherTreeList = AdminFatherTreeList;
    }
    
    
}
