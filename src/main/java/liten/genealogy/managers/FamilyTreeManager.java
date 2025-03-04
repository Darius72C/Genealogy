/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.FamilyTree;
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
public class FamilyTreeManager implements Serializable{
    
    public FamilyTreeManager(){
        
    }
    
    public FamilyTree addNewFamilyTree(FamilyTree familyTree){
        FamilyTree familyTreeDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(familyTree != null && familyTree.getFamilyTreeID() == null && familyTree.getRootPersonID() == null && familyTree.getEtnicGroupID() == null && familyTree.getName() != null && familyTree.getDescription() != null){
                String familyTreeID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.FamilyTree"));
                familyTree.setFamilyTreeID(familyTreeID);
            }
            familyTreeDB = connectionServiceFacade.mergeEntity(familyTree);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return familyTreeDB;
    }
    
    public void updateFamilyTree (FamilyTree familyTree){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            FamilyTree familyTreeDB = MainDAO.findFamilyTree(familyTree.getFamilyTreeID());
            if(familyTreeDB != null) {
                familyTree = connectionServiceFacade.mergeEntity(familyTree);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void deleteFamilyTree(String familyTreeID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            FamilyTree familyTreeDB = MainDAO.findFamilyTree(familyTreeID);
            if (familyTreeDB != null) {
                connectionServiceFacade.removeEntity(familyTreeDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FamilyTree findFamilyTree(String familyTreeID){
        FamilyTree familyTree = null;
        try{
            familyTree = MainDAO.findFamilyTree(familyTreeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return familyTree;
    }
    
    public List<FamilyTree> findAllFamilyTree() {
        List<FamilyTree> myFamilyTreeList = MainDAO.findAllFamilyTree();
        if (myFamilyTreeList == null) {
            myFamilyTreeList = new ArrayList<FamilyTree>();
        }
        return myFamilyTreeList;
    }
    
    public FamilyTree findFamilyTreePerName(String name){
        return MainDAO.findFamilyTree(name);
    }
    
    public FamilyTree findFamilyTreePerDescription(String description){
        return MainDAO.findFamilyTree(description);
    }
    
}
