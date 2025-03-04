/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Enrollment;
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
public class EnrollmentManager implements Serializable{
    
    public EnrollmentManager(){

    }
    
    public Enrollment addNewEnrollment(Enrollment enrollment){
        Enrollment enrollmentDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(enrollment != null && enrollment.getEnrollmentID() == null){
                String enrollmentID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Enrollment"));
                enrollment.setEnrollmentID(enrollmentID);
            }
            enrollmentDB = connectionServiceFacade.mergeEntity(enrollment);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return enrollmentDB;
    }
    
    public Enrollment updateEnrollment (Enrollment enrollment){
        Enrollment enrollmentDB=null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            enrollmentDB = MainDAO.findEnrollment(enrollment.getEnrollmentID());
            if(enrollmentDB != null) {
                enrollment = connectionServiceFacade.mergeEntity(enrollment);
            }
        } catch(Exception e){
            e.printStackTrace();
        }     
        return enrollmentDB;
    }
    
    public void deleteEnrollment(String enrollmentID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Enrollment enrollmentDB = MainDAO.findEnrollment(enrollmentID);
            if (enrollmentDB != null) {
                connectionServiceFacade.removeEntity(enrollmentDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Enrollment findEnrollment(String enrollmentID){
        Enrollment enrollment = null;
        try{
            enrollment = MainDAO.findEnrollment(enrollmentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enrollment;
    }
    
    public List<Enrollment> findAllEnrollment() {
        List<Enrollment> myEnrollmentList = MainDAO.findAllEnrollment();
        if (myEnrollmentList == null) {
            myEnrollmentList = new ArrayList<Enrollment>();
        }
        return myEnrollmentList;
    }
}
