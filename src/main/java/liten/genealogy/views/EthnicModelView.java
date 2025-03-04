/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.EthnicGroup;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class EthnicModelView implements Serializable{
    String ethnicGroupID;
    EthnicGroup ethnicGroup;
    List<EthnicGroup> ethnicGroupList;

    public EthnicModelView() {
    }

    public String getEthnicGroupID() {
        return ethnicGroupID;
    }

    public void setEthnicGroupID(String ethnicGroupID) {
        this.ethnicGroupID = ethnicGroupID;
    }

    public EthnicGroup getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(EthnicGroup ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public List<EthnicGroup> getEthnicGroupList() {
        return ethnicGroupList;
    }

    public void setEthnicGroupList(List<EthnicGroup> ethnicGroupList) {
        this.ethnicGroupList = ethnicGroupList;
    }
    
    
}
