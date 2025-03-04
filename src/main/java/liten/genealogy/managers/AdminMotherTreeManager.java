/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.AdminMotherTree;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.KeyGeneration;
import liten.genealogy.utilitiesDB.MainDAO;

/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class AdminMotherTreeManager implements Serializable {

    public AdminMotherTreeManager() {

    }

    public AdminMotherTree addNewAdminMyFamilyTree(AdminMotherTree adminMotherTree) {
        AdminMotherTree adminMotherTreeDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (adminMotherTree != null && adminMotherTree.getAdmin().getPersonID() == null && adminMotherTree.getFamilyTree().getFamilyTreeID() != null) {
                adminMotherTreeDB = connectionServiceFacade.mergeEntity(adminMotherTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminMotherTreeDB;
    }

    public void updateAdminMotherTree(AdminMotherTree adminMotherTree) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            AdminMotherTree adminMotherTreeDB = MainDAO.findAdminMotherTree(adminMotherTree.getAdmin().getPersonID(), adminMotherTree.getFamilyTree().getFamilyTreeID());
            if (adminMotherTreeDB != null) {
                adminMotherTree = connectionServiceFacade.mergeEntity(adminMotherTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdminMotherTree(String adminID, String motherFamailyTreeID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            AdminMotherTree adminMotherTreeDB = MainDAO.findAdminMotherTree(adminID, motherFamailyTreeID);
            if (adminMotherTreeDB != null) {
                connectionServiceFacade.removeEntity(adminMotherTreeDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminMotherTree findAdminMotherTree(String adminID, String motherFamailyTreeID) {
        AdminMotherTree adminMotherTree = null;
        try {
            adminMotherTree = MainDAO.findAdminMotherTree(adminID, motherFamailyTreeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminMotherTree;
    }

    public List<AdminMotherTree> findAllAdminMotherTree() {
        List<AdminMotherTree> myAdminMotherTreeList = MainDAO.findAllAdminMotherTree();
        if (myAdminMotherTreeList == null) {
            myAdminMotherTreeList = new ArrayList<AdminMotherTree>();
        }
        return myAdminMotherTreeList;
    }
}
