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
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "knownunions")
@NamedQueries({
    @NamedQuery(name = "KnownUnions.findAll", query = "SELECT k FROM KnownUnions k"),
    @NamedQuery(name = "KnownUnions.findByHusbandID", query = "SELECT k FROM KnownUnions k WHERE k.knownUnionsPK.husbandID = :husbandID"),
    @NamedQuery(name = "KnownUnions.findByWifeID", query = "SELECT k FROM KnownUnions k WHERE k.knownUnionsPK.wifeID = :wifeID"),
    @NamedQuery(name = "KnownUnions.findByStartedDate", query = "SELECT k FROM KnownUnions k WHERE k.startedDate = :startedDate"),
    @NamedQuery(name = "KnownUnions.findByEndedDate", query = "SELECT k FROM KnownUnions k WHERE k.endedDate = :endedDate"),
    @NamedQuery(name = "KnownUnions.findByNbrOfChildren", query = "SELECT k FROM KnownUnions k WHERE k.nbrOfChildren = :nbrOfChildren"),
    @NamedQuery(name = "KnownUnions.findByNbrOfBoys", query = "SELECT k FROM KnownUnions k WHERE k.nbrOfBoys = :nbrOfBoys"),
    @NamedQuery(name = "KnownUnions.findByNbrOfGirls", query = "SELECT k FROM KnownUnions k WHERE k.nbrOfGirls = :nbrOfGirls"),
    @NamedQuery(name = "KnownUnions.findByValidateDate", query = "SELECT k FROM KnownUnions k WHERE k.validateDate = :validateDate"),
    @NamedQuery(name = "KnownUnions.findByCreatedDate", query = "SELECT k FROM KnownUnions k WHERE k.createdDate = :createdDate"),
    @NamedQuery(name = "KnownUnions.findByModifiedDate", query = "SELECT k FROM KnownUnions k WHERE k.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class KnownUnions implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "startedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedDate;
    @Column(name = "endedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validateDate;
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
    @JoinColumn(name = "wifeID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person wife;
    @JoinColumn(name = "husbandID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person husband;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KnownUnionsPK knownUnionsPK;
    @Column(name = "nbrOfChildren")
    private Integer nbrOfChildren;
    @Column(name = "nbrOfBoys")
    private Integer nbrOfBoys;
    @Column(name = "nbrOfGirls")
    private Integer nbrOfGirls;
      @JoinColumn(name = "updateBy", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person updateBy;


    public KnownUnions() {
    }

    public KnownUnions(KnownUnionsPK knownUnionsPK) {
        this.knownUnionsPK = knownUnionsPK;
    }

    public KnownUnions(KnownUnionsPK knownUnionsPK, Date startedDate, Date endedDate, Date validateDate, Date createdDate, Date modifiedDate) {
        this.knownUnionsPK = knownUnionsPK;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
        this.validateDate = validateDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public KnownUnions(String husbandID, String wifeID) {
        this.knownUnionsPK = new KnownUnionsPK(husbandID, wifeID);
    }

    public KnownUnionsPK getKnownUnionsPK() {
        return knownUnionsPK;
    }

    public void setKnownUnionsPK(KnownUnionsPK knownUnionsPK) {
        this.knownUnionsPK = knownUnionsPK;
    }


    public Integer getNbrOfChildren() {
        return nbrOfChildren;
    }

    public void setNbrOfChildren(Integer nbrOfChildren) {
        this.nbrOfChildren = nbrOfChildren;
    }

    public Integer getNbrOfBoys() {
        return nbrOfBoys;
    }

    public void setNbrOfBoys(Integer nbrOfBoys) {
        this.nbrOfBoys = nbrOfBoys;
    }

    public Integer getNbrOfGirls() {
        return nbrOfGirls;
    }

    public void setNbrOfGirls(Integer nbrOfGirls) {
        this.nbrOfGirls = nbrOfGirls;
    }

    public Person getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Person updateBy) {
        this.updateBy = updateBy;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (knownUnionsPK != null ? knownUnionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KnownUnions)) {
            return false;
        }
        KnownUnions other = (KnownUnions) object;
        if ((this.knownUnionsPK == null && other.knownUnionsPK != null) || (this.knownUnionsPK != null && !this.knownUnionsPK.equals(other.knownUnionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.KnownUnions[ knownUnionsPK=" + knownUnionsPK + " ]";
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
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

    public Person getWife() {
        return wife;
    }

    public void setWife(Person wife) {
        this.wife = wife;
    }

    public Person getHusband() {
        return husband;
    }

    public void setHusband(Person husband) {
        this.husband = husband;
    }
    
}
