/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.AdminFatherTree;
import liten.genealogy.mainEntities.AdminFatherTreePK;
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
public class AdminFatherTreeManager implements Serializable {

    public AdminFatherTreeManager() {

    }

    public AdminFatherTree addNewAdminMyFamilyTree(AdminFatherTree adminFatherTree) {
        AdminFatherTree adminFatherTreeDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (adminFatherTree != null && adminFatherTree.getAdmin().getPersonID() == null && adminFatherTree.getFamilyTree().getFamilyTreeID() != null) {
                adminFatherTreeDB = connectionServiceFacade.mergeEntity(adminFatherTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminFatherTreeDB;
    }

    public void updateAdminFatherTree(AdminFatherTreePK adminFatherTree) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            AdminFatherTree adminFatherTreeDB = MainDAO.findAdminFatherTree(adminFatherTree.getAdminID(), adminFatherTree.getFamilyTreeID());
            if (adminFatherTreeDB != null) {
                adminFatherTree = connectionServiceFacade.mergeEntity(adminFatherTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdminFatherTree(String adminID, String fatherID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            AdminFatherTree adminFatherTreeDB = MainDAO.findAdminFatherTree(adminID, fatherID);
            if (adminFatherTreeDB != null) {
                connectionServiceFacade.removeEntity(adminFatherTreeDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminFatherTree findAdminFatherTree(String adminID, String fatherID) {
        AdminFatherTree adminFatherTree = null;
        try {
            adminFatherTree = MainDAO.findAdminFatherTree(adminID, fatherID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminFatherTree;
    }

    public List<AdminFatherTree> findAllAdminFatherTree() {
        List<AdminFatherTree> myAdminFatherTreeList = MainDAO.findAllAdminFatherTree();
        if (myAdminFatherTreeList == null) {
            myAdminFatherTreeList = new ArrayList<AdminFatherTree>();
        }
        return myAdminFatherTreeList;
    }
}
