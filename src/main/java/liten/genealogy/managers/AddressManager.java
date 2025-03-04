/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Country;
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
public class AddressManager implements Serializable {

    public AddressManager() {

    }

    public Country addNewCountry(Country country) {
        Country countryDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (country != null && country.getCountryID() == null) {
                String countryID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Country"));
                country.setCountryID(countryID);
            }
            countryDB = connectionServiceFacade.mergeEntity(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryDB;
    }

    public Country updateCountry(Country country) {
        Country countryDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            countryDB = MainDAO.findCountry(country.getCountryID());
            if (countryDB != null) {
                countryDB = connectionServiceFacade.mergeEntity(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryDB;
    }

    public void deleteCountry(String countryID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Country countryDB = MainDAO.findCountry(countryID);
            if (countryDB != null) {
                connectionServiceFacade.removeEntity(countryDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
