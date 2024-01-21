/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Transient;
import java.time.Year;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.PostLoad;

/**
 *
 * @author dell
 */
@Entity
@Table(catalog = "controle", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machine.findAll", query = "SELECT m FROM Machine m"),
    @NamedQuery(name = "Machine.findById", query = "SELECT m FROM Machine m WHERE m.id = :id"),
    @NamedQuery(name = "Machine.findByReference", query = "SELECT m FROM Machine m WHERE m.reference = :reference"),
    @NamedQuery(name = "Machine.findByMarque", query = "SELECT m FROM Machine m WHERE m.marque = :marque"),
    @NamedQuery(name = "Machine.findByDateAchat", query = "SELECT m FROM Machine m WHERE m.dateAchat = :dateAchat"),
    @NamedQuery(name = "Machine.findByPrix", query = "SELECT m FROM Machine m WHERE m.prix = :prix")})
    @NamedQuery(name = "Machine.search", query = "SELECT m FROM Machine m WHERE m.reference LIKE :searchTerm OR m.marque LIKE :searchTerm")

public class Machine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String reference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String marque;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double prix;
    @Transient
    private Year acquisitionYear;
    @JoinColumn(name = "Employee", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Employee employee;
    

    public Machine() {
    }

    public Machine(Integer id) {
        this.id = id;
    }

    public Machine(Integer id, String reference, String marque, Date dateAchat, double prix, Year acquisitionYear, Employee employee) {
        this.id = id;
        this.reference = reference;
        this.marque = marque;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.acquisitionYear = acquisitionYear;
        this.employee = employee;
    }

    public Year getAcquisitionYear() {
        return acquisitionYear;
    }

    public void setAcquisitionYear(Year acquisitionYear) {
        this.acquisitionYear = acquisitionYear;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Machine)) {
            return false;
        }
        Machine other = (Machine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @PostLoad
    private void calculateAcquisitionYear() {
        if (dateAchat != null) {
            LocalDate acquisitionDate = dateAchat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            acquisitionYear = Year.of(acquisitionDate.getYear());
        }
    }

    @Override
    public String toString() {
        return "Controle.Machine[ id=" + id + " ]";
    }
    
}
