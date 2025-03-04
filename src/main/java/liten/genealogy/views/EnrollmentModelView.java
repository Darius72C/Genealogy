/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.Enrollment;
import liten.genealogy.managers.ClientManager;
import liten.genealogy.managers.EnrollmentManager;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.webUtilities.HttpUtils;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class EnrollmentModelView implements Serializable {
       String enrollmentID;
    Enrollment enrollment;
    Date activeEnrollmentStartDate;
    Date activeEnrollmentEndDate;
    double activeEnrollmentCost;
    List<Enrollment> enrollmentList;
    String clientID;
    String clientCode;
    Client client;
    List<Client> clientList;

    public EnrollmentModelView() {
                enrollment = new Enrollment();
        enrollment.setClient(new Client());
        activeEnrollmentStartDate = MainDAO.convertStringToDate(ConstantValues.ACTIVE_ENROLLMENT_START_DATE);
        activeEnrollmentEndDate = MainDAO.convertStringToDate(ConstantValues.ACTIVE_ENROLLMENT_END_DATE);
        activeEnrollmentCost = ConstantValues.ACTIVE_ENROLLMENT_COST;
        EnrollmentManager enrollmentManager = new EnrollmentManager();
        enrollmentList = enrollmentManager.findAllEnrollment();
        AccountModelView accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        if (accountModelView.authenticated) {
            client = accountModelView.getAccount().getClient();
            clientID = client.getClientID();
            enrollmentList = MainDAO.findAllEnrollmentPerClient(clientID);
        }
        if (enrollmentList == null) {
            enrollmentList = new ArrayList<Enrollment>();
        }
        ClientManager clientManager = new ClientManager();
        clientList = clientManager.findAllClient();
        if (enrollmentList == null) {
            clientList = new ArrayList<Client>();
        }
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }
    
    public Client getClient() {
        return client;
    }

    public String getClientCode() {
        return clientCode;
    }

    public Date getActiveEnrollmentStartDate() {
        return activeEnrollmentStartDate;
    }

    public Date getActiveEnrollmentEndDate() {
        return activeEnrollmentEndDate;
    }

    public double getActiveEnrollmentCost() {
        return activeEnrollmentCost;
    }

    public Enrollment getLatestEnrollment(String clientID) {
        enrollment = MainDAO.findLastEnrollment(clientID);
        return enrollment;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setClientCode(String clientID) {
        this.clientCode = clientCode;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setActiveEnrollmentStartDate(Date startDate) {
        activeEnrollmentStartDate = startDate;
    }

    public void setActiveEnrollmentEndDate(Date endDate) {
        this.activeEnrollmentEndDate = endDate;
    }

    public void setActiveEnrollmentCost(double cost) {
        this.activeEnrollmentCost = cost;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public void myActiveEnrollment() {
        AccountModelView accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        if (accountModelView != null && accountModelView.getAuthenticated() && accountModelView.getAccount().getAccountType().equals("Client")) {;
            enrollment = MainDAO.findActiveEnrollment(accountModelView.getAccount().getClient().getClientID());
        }
    }

    public void myPreviousEnrollment() {
        AccountModelView accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        if (accountModelView != null && accountModelView.getAuthenticated() && accountModelView.getAccount().getAccountType().equals("Client")) {;
            enrollment = MainDAO.findLastEnrollment(accountModelView.getAccount().getClient().getClientID());
        }
    }

    public void myHistoricalEnrollments() {
        AccountModelView accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        if (accountModelView != null && accountModelView.getAuthenticated() && accountModelView.getAccount().getAccountType().equals("Client")) {;
            enrollmentList = MainDAO.findAllEnrollmentPerClient(accountModelView.getAccount().getClient().getClientID());
        }
    }

    
    
}
