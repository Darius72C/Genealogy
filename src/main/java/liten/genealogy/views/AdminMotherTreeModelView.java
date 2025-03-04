/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.AdminMotherTree;
import liten.genealogy.mainEntities.AdminMotherTreePK;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class AdminMotherTreeModelView implements Serializable{
    String adminID;
    String motherID;
    AdminMotherTree adminMMT;
    List<AdminMotherTree> adminMotherTreeList;

    public AdminMotherTreeModelView() {
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public AdminMotherTree getAdminMMT() {
        return adminMMT;
    }

    public void setAdminMMT(AdminMotherTree adminMMT) {
        this.adminMMT = adminMMT;
    }

    public List<AdminMotherTree> getAdminMotherTreeList() {
        return adminMotherTreeList;
    }

    public void setAdminMotherTreeList(List<AdminMotherTree> adminMotherTreeList) {
        this.adminMotherTreeList = adminMotherTreeList;
    }
    
    
}
