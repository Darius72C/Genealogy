/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.FamilyTree;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class FamilyTreeModelView implements Serializable {
    String FamilyTreeID;
    FamilyTree familyTree;
    List<FamilyTree> FamilyTreeList;

    public FamilyTreeModelView() {
    }

    public String getFamilyTreeID() {
        return FamilyTreeID;
    }

    public void setFamilyTreeID(String FamilyTreeID) {
        this.FamilyTreeID = FamilyTreeID;
    }

    public FamilyTree getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    public List<FamilyTree> getFamilyTreeList() {
        return FamilyTreeList;
    }

    public void setFamilyTreeList(List<FamilyTree> FamilyTreeList) {
        this.FamilyTreeList = FamilyTreeList;
    }
    
    
}
