/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webListeners;

import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.Enrollment;
import liten.genealogy.managers.ClientManager;
import liten.genealogy.managers.EnrollmentManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import liten.genealogy.views.AccountModelView;
import liten.genealogy.views.EnrollmentModelView;
import org.primefaces.PrimeFaces;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.webUtilities.HttpUtils;

/**
 *
 * @author FACULTY
 */
@Named
@SessionScoped
public class EnrollmentListener implements Serializable {

    String origURI;
    @Inject
    EnrollmentModelView enrollmentModelView;
    @Inject
    AccountModelView accountModelView;
    @Inject
    EnrollmentManager enrollmentManager;

    public EnrollmentListener() {

    }

    @PostConstruct
    public void init() {
        if (HttpUtils.getSessionMap().get("enrollmentModelView") == null) {
            HttpUtils.getSessionMap().put("enrollmentModelView", enrollmentModelView);
        } else {
            enrollmentModelView = (EnrollmentModelView) HttpUtils.getSessionMap().get("enrollmentModelView");
        }
        if (HttpUtils.getSessionMap().get("accountModelView") == null) {
            accountModelView.init();
            HttpUtils.getSessionMap().put("accountModelView", accountModelView);
        } else {
            accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        }
    }

    public void setOrigURI(String curURI) {
        this.origURI = curURI;
    }

    public String getOrigURI() {
        return origURI;
    }

    public EnrollmentManager getEnrollmentManager() {
        return this.enrollmentManager;
    }

    public EnrollmentModelView getEnrollmentModelView() {
        return this.enrollmentModelView;
    }

    public AccountModelView getAccountModelView() {
        return this.accountModelView;
    }

    public void addEnrollment(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String form = (String) HttpUtils.getRequestFormID();
            String startDateID = (String) HttpUtils.getRequestParamsMap().get(form + ":startDateID");
            Date startDate = MainDAO.convertStringToDate(startDateID);
            String clientID = enrollmentModelView.getClientID();
            Client client = MainDAO.findClient(clientID);
            Enrollment enrollment = MainDAO.findEnrollmentPerEndofLifeAndDates(clientID, startDate, startDate);
            if (enrollment == null) {
                enrollment = enrollmentModelView.getEnrollment();
                enrollment.setClient(client);
                enrollment.setIsValid(false);
                enrollment.setStatus("Waiting for payment");
                Enrollment enrollmentDB = enrollmentManager.addNewEnrollment(enrollment);
                if (enrollmentDB != null) {
                    enrollment = enrollmentDB;
                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Enrollment: " + enrollment.getClient().getPerson().getLastName() + " " + enrollment.getClient().getPerson().getFirstName(), "a été créé et sauvegardé ave succès!"));
                    uri = contextPath + "/payment/paymentProcess.xhtml";
                    HttpUtils.getFacesExternalContext().redirect(uri);
                } else {
                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Enrollment not saved properly!", "Please retry!"));
                }
            }else{
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Already enrolled!", "You are already enrolled  and your enollment is active!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEnrollment(ActionEvent evt) {
        try {
            Enrollment enrollment = enrollmentModelView.getEnrollment();
            Enrollment enrollmentDB = enrollmentManager.updateEnrollment(enrollment);
            if (enrollmentDB != null) {
                enrollment = enrollmentDB;
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Enrollment: " + enrollment.getClient().getPerson().getLastName() + " " + enrollment.getClient().getPerson().getFirstName(), "updated successfully!"));
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("No update!", "Please check ypur data!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectClient(AjaxBehaviorEvent event) {
        try {
            String clientID = enrollmentModelView.getClientID();
            if (clientID != null) {
                ClientManager clientManager = new ClientManager();
                Client client = clientManager.findClient(clientID);
                enrollmentModelView.setClient(client);
            }
            String uri = HttpUtils.getRequestURI();
            System.out.println(uri);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectEnrollment(AjaxBehaviorEvent event) {
        try {
            String enrollmentID = enrollmentModelView.getEnrollmentID();
            Enrollment enrollment = MainDAO.findEnrollment(enrollmentID);
            enrollmentModelView.setEnrollment(enrollment);
            String uri = HttpUtils.getRequestURI();
            System.out.println(uri);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public void clearEnrollment(ActionEvent evt) {
        enrollmentModelView.setClientCode("");
    }

}
