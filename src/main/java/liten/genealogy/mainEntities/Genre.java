/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "genre")
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findByGenreID", query = "SELECT g FROM Genre g WHERE g.genreID = :genreID"),
    @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name"),
    @NamedQuery(name = "Genre.findByDescription", query = "SELECT g FROM Genre g WHERE g.description = :description"),
    @NamedQuery(name = "Genre.findByCreatedDate", query = "SELECT g FROM Genre g WHERE g.createdDate = :createdDate"),
    @NamedQuery(name = "Genre.findByModifiedDate", query = "SELECT g FROM Genre g WHERE g.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class Genre implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(mappedBy = "genre")
    private List<Person> personList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<PersonGenre> personGenreList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "genreID")
    private String genreID;

    public Genre() {
    }

    public Genre(String genreID) {
        this.genreID = genreID;
    }

    public Genre(String genreID, String name, String description, Date createdDate, Date modifiedDate) {
        this.genreID = genreID;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreID != null ? genreID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.genreID == null && other.genreID != null) || (this.genreID != null && !this.genreID.equals(other.genreID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Genre[ genreID=" + genreID + " ]";
    }
    @XmlTransient
    public List<PersonGenre> getPersonGenreList() {
        return personGenreList;
    }
    public void setPersonGenreList(List<PersonGenre> personGenreList) {
        this.personGenreList = personGenreList;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    
}
