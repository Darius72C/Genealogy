/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "clienttype")
@NamedQueries({
    @NamedQuery(name = "ClientType.findAll", query = "SELECT c FROM ClientType c"),
    @NamedQuery(name = "ClientType.findByClientTypeID", query = "SELECT c FROM ClientType c WHERE c.clientTypeID = :clientTypeID"),
    @NamedQuery(name = "ClientType.findByName", query = "SELECT c FROM ClientType c WHERE c.name = :name"),
    @NamedQuery(name = "ClientType.findByDescription", query = "SELECT c FROM ClientType c WHERE c.description = :description")})
@Named
@RequestScoped   
public class ClientType implements Serializable {

    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "description")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clientTypeID")
    private String clientTypeID;
    @OneToMany(mappedBy = "clientType")
    @JsonBackReference
    private List<Client> clientList;

    public ClientType() {
    }

    public ClientType(String clientTypeID) {
        this.clientTypeID = clientTypeID;
    }

    public String getClientTypeID() {
        return clientTypeID;
    }

    public void setClientTypeID(String clientTypeID) {
        this.clientTypeID = clientTypeID;
    }


    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientTypeID != null ? clientTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientType)) {
            return false;
        }
        ClientType other = (ClientType) object;
        if ((this.clientTypeID == null && other.clientTypeID != null) || (this.clientTypeID != null && !this.clientTypeID.equals(other.clientTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.ClientType[ clientTypeID=" + clientTypeID + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
