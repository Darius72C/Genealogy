/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.EthnicGroup;
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
public class EthnicManager implements Serializable {
    
    public EthnicManager(){
        
    }
    
    public EthnicGroup addNewEthnicGroup(EthnicGroup ethnicGroup){
        EthnicGroup ethnicGroupDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(ethnicGroup != null & ethnicGroup.getEthnicGroupID() == null && ethnicGroup.getName() != null){
                String ethnicGroupID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.EthnicGroup"));
                ethnicGroup.setEthnicGroupID(ethnicGroupID);
            }
            ethnicGroupDB = connectionServiceFacade.mergeEntity(ethnicGroup);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return ethnicGroupDB;
    }
    
    public void updateEthnicGroup (EthnicGroup ethnicGroup){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            EthnicGroup ethnicGroupDB = MainDAO.findEthnicGroup(ethnicGroup.getEthnicGroupID());
            if(ethnicGroupDB != null) {
                ethnicGroup = connectionServiceFacade.mergeEntity(ethnicGroup);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void deleteEthnicGroup(String ethnicGroupID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            EthnicGroup ethnicGroupDB = MainDAO.findEthnicGroup(ethnicGroupID);
            if (ethnicGroupDB != null) {
                connectionServiceFacade.removeEntity(ethnicGroupDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public EthnicGroup findEthnicGroup(String ethnicGroupID){
        EthnicGroup ethnicGroup = null;
        try{
            ethnicGroup = MainDAO.findEthnicGroup(ethnicGroupID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethnicGroup;
    }
    
    public List<EthnicGroup> findAllEthnicGroup() {
        List<EthnicGroup> myEthnicGroupList = MainDAO.findAllEthnicGroup();
        if (myEthnicGroupList == null) {
            myEthnicGroupList = new ArrayList<EthnicGroup>();
        }
        return myEthnicGroupList;
    }
    
    public EthnicGroup findEthnicGroupPerName(String name){
        return MainDAO.findEthnicGroup(name);
    }
}
