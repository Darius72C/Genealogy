/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.webListeners;

/**
 *
 * @author FACULTY
 */
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.views.AddressModelView;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.managers.AddressManager;
import liten.genealogy.mainEntities.Country;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Date;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class AddressListener implements Serializable {
    
    AddressModelView addressModelView;
    @Inject
    AddressManager addressManager;
    String origURI = "";
    FacesMessage message;
    boolean isCreated = false;
    boolean enableLoad = false;
    boolean enableRegionPanel = true;
    boolean enableCityPanel = false;
    boolean enableQuarterPanel = false;
    
    public AddressListener() {
        
    }
    
    @PostConstruct
    public void init() {
        try {
            if (HttpUtils.getSessionMap().get("addressModelView") == null) {
                addressModelView = new AddressModelView();
                HttpUtils.getSessionMap().put("addressModelView", addressModelView);
            } else {
                addressModelView = (AddressModelView) HttpUtils.getSessionMap().get("addressModelView");
                addressModelView.setCountry(new Country());
                HttpUtils.getSessionMap().put("addressModelView", addressModelView);
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
    
    public AddressModelView getAddressModelView() {
        return addressModelView;
    }
    
    public AddressManager getAddressManager() {
        return addressManager;
    }
    
    public void addCountry(ActionEvent evt) {
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String formID = HttpUtils.getRequestFormID();
            Country country = addressModelView.getCountry();
            country = addressManager.addNewCountry(country);
            if (country != null) {
                addressModelView.setCountry(country);
                String uri = HttpUtils.getRequestURI();
                message = new FacesMessage("Pays créée avec succès", "Pays a été créée avec succès!");
                HttpUtils.getFacesContext().addMessage("Pays créé!", message);
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else {
                message = new FacesMessage("Pays non créé...Vérifiez les informations requises et le format requis!");
                HttpUtils.getFacesContext().addMessage("Réessayez, données rejetées!", message);
            }
            PrimeFaces.current().ajax().update(formID);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCountry(ActionEvent evt) {
        try {
            Country country = addressModelView.getCountry();
            if (country.getCountryID() != null) {
                Country countryDB = MainDAO.findCountry(country.getCountryID());
                if (countryDB != null) {
                    countryDB = addressManager.updateCountry(country);
                    addressModelView.setCountry(country);
                    String uri = HttpUtils.getRequestURI();
                    message = new FacesMessage("Pays mis à jour!");
                    HttpUtils.addMessage(message);
                    HttpUtils.getFacesExternalContext().redirect(uri);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectCountry(ActionEvent evt) {
        try {
            String countryID = addressModelView.getCountryID();
            Country country = MainDAO.findCountry(countryID);
            addressModelView.setCountry(country);
            String uri = HttpUtils.getRequestURI();
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearCountry(ActionEvent evt) {
        addressModelView.setCountryID("");
        addressModelView.getCountry().setDescription("");
        addressModelView.getCountry().setName("");
    }
    
}
