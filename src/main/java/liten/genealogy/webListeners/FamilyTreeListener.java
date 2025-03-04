/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.webListeners;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import liten.genealogy.mainEntities.FamilyTree;
import liten.genealogy.mainEntities.AdminMotherTree;
import liten.genealogy.mainEntities.Country;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.managers.FamilyTreeManager;
import liten.genealogy.managers.AdminMotherTreeManager;
import org.primefaces.PrimeFaces;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.views.FamilyTreeModelView;
import liten.genealogy.views.AdminFatherTreeModelView;
import liten.genealogy.views.AdminMotherTreeModelView;
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.webUtilities.SendEmail;

/**
 *
 * @author bayomock.a
 */
@Named
@SessionScoped
public class FamilyTreeListener implements Serializable {

    FamilyTreeModelView familyTreeModelView;
    AdminFatherTreeModelView adminFatherTreeModelView;
    AdminMotherTreeModelView adminMotherTreeModelView;
    @Inject
    FamilyTreeManager familyTreeManager;
    @Inject
    AdminMotherTreeManager adminMotherTreeManager;
    String origURI = "";
    FacesMessage message;

    public FamilyTreeListener() {

    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("Dans init");
            if (HttpUtils.getSessionMap().get("familyTreeModelView") == null) {
                familyTreeModelView = new FamilyTreeModelView();
                HttpUtils.getSessionMap().put("familyTreeModelView", familyTreeModelView);
            } else {
                System.out.println("Dans init 2");
                familyTreeModelView = (FamilyTreeModelView) HttpUtils.getSessionMap().get("familyTreeModelView");
            }
            if (HttpUtils.getSessionMap().get("adminFatherTreeModelView") == null) {
                adminFatherTreeModelView = new AdminFatherTreeModelView();
                HttpUtils.getSessionMap().put("adminFatherTreeModelView", adminFatherTreeModelView);
            } else {
                adminFatherTreeModelView = (AdminFatherTreeModelView) HttpUtils.getSessionMap().get("adminFatherTreeModelView");
            }
            if (HttpUtils.getSessionMap().get("adminMotherTreeModelView") == null) {
                adminMotherTreeModelView = new AdminMotherTreeModelView();
                HttpUtils.getSessionMap().put("adminMotherTreeModelView", adminMotherTreeModelView);
            } else {
                adminMotherTreeModelView = (AdminMotherTreeModelView) HttpUtils.getSessionMap().get("adminMotherTreeModelView");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrigURI(String curURI) {
        this.origURI = curURI;
    }

    public String getOrigURI() {
        return origURI;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public FamilyTreeModelView getFamilyTreeModelView() {
        return familyTreeModelView;
    }

    public AdminFatherTreeModelView getAdminFatherTreeModelView() {
        return adminFatherTreeModelView;
    }

    public AdminMotherTreeModelView getAdminMotherTreeModelView() {
        return adminMotherTreeModelView;
    }

    public FamilyTreeManager getFamilyTreeManager() {
        return familyTreeManager;
    }

    public void addAdminMotherTree(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String formID = HttpUtils.getRequestFormID();
            FacesContext fc = FacesContext.getCurrentInstance();
            origURI = "/adminMotherTree/ajouterAdminMotherTree.xhtml";
            String adminID = adminMotherTreeModelView.getAdminID();
            Person father = MainDAO.findPerson(adminID);
            String motherID = adminMotherTreeModelView.getMotherID();
            Person mother = MainDAO.findPerson(motherID);
            AdminMotherTree adminMotherTree = adminMotherTreeModelView.getAdminMMT();
            
           // adminMotherTree.set
            FamilyTree familyTree = familyTreeModelView.getFamilyTree();
            uri = HttpUtils.getRequestURI();
            message = new FacesMessage("Compte adminMotherTree créé avec succès", "Lire votre email et confirmer la création du compte!");
            HttpUtils.getFacesContext().addMessage("Compte créé!", message);
            HttpUtils.getFacesExternalContext().redirect(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAdminMotherTree(ActionEvent evt) {
        try {
            AdminMotherTree adminMotherTree = adminMotherTreeModelView.getAdminMMT();
            AdminMotherTree adminMotherTreeDB = adminMotherTreeManager.findAdminMotherTree(adminMotherTree.getAdmin().getPersonID(), adminMotherTree.getFamilyTree().getFamilyTreeID());
            if (adminMotherTreeDB != null) {
                adminMotherTreeManager.updateAdminMotherTree(adminMotherTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
