package com.firecode.app.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "city", catalog = "realestate", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})

public class CityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 2)
    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "code_ibge")
    private Integer codeIbge;

    @Size(max = 2)
    @Column(name = "ddd", length = 2)
    private String ddd;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCity")
    private Collection<StreetEntity> streetEntityCollection;

    public CityEntity() {
    }

    public CityEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCodeIbge() {
        return codeIbge;
    }

    public void setCodeIbge(Integer codeIbge) {
        this.codeIbge = codeIbge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Collection<StreetEntity> getStreetEntityCollection() {
        return streetEntityCollection;
    }

    public void setStreetEntityCollection(Collection<StreetEntity> streetEntityCollection) {
        this.streetEntityCollection = streetEntityCollection;
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
        if (!(object instanceof CityEntity)) {
            return false;
        }
        CityEntity other = (CityEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.CityEntity[ id=" + id + " ]";
    }

}
