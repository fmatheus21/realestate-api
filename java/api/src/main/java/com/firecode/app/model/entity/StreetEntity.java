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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "street", catalog = "realestate", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"zip_code"})})

public class StreetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "zip_code", nullable = false, length = 11)
    private String zipCode;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Size(max = 100)
    @Column(name = "complement", length = 100)
    private String complement;

    @Size(max = 100)
    @Column(name = "description_without_number", length = 100)
    private String descriptionWithoutNumber;

    @Size(max = 100)
    @Column(name = "description_city", length = 100)
    private String descriptionCity;

    @Column(name = "code_city_ibge")
    private Integer codeCityIbge;

    @Size(max = 100)
    @Column(name = "description_district", length = 100)
    private String descriptionDistrict;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStreet")
    private Collection<AddressEntity> addressEntityCollection;

    @JoinColumn(name = "id_city", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private CityEntity idCity;

    public StreetEntity() {
    }

    public StreetEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDescriptionWithoutNumber() {
        return descriptionWithoutNumber;
    }

    public void setDescriptionWithoutNumber(String descriptionWithoutNumber) {
        this.descriptionWithoutNumber = descriptionWithoutNumber;
    }

    public String getDescriptionCity() {
        return descriptionCity;
    }

    public void setDescriptionCity(String descriptionCity) {
        this.descriptionCity = descriptionCity;
    }

    public Integer getCodeCityIbge() {
        return codeCityIbge;
    }

    public void setCodeCityIbge(Integer codeCityIbge) {
        this.codeCityIbge = codeCityIbge;
    }

    public String getDescriptionDistrict() {
        return descriptionDistrict;
    }

    public void setDescriptionDistrict(String descriptionDistrict) {
        this.descriptionDistrict = descriptionDistrict;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AddressEntity> getAddressEntityCollection() {
        return addressEntityCollection;
    }

    public void setAddressEntityCollection(Collection<AddressEntity> addressEntityCollection) {
        this.addressEntityCollection = addressEntityCollection;
    }

    public CityEntity getIdCity() {
        return idCity;
    }

    public void setIdCity(CityEntity idCity) {
        this.idCity = idCity;
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
        if (!(object instanceof StreetEntity)) {
            return false;
        }
        StreetEntity other = (StreetEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.PublicPlaceEntity[ id=" + id + " ]";
    }

}
