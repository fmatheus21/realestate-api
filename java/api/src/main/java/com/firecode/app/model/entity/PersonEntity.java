package com.firecode.app.model.entity;

import com.firecode.app.controller.util.AppUtil;
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
import javax.persistence.OneToOne;
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
@Table(name = "person", catalog = "realestate", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"document"}),
    @UniqueConstraint(columnNames = {"id"})})

public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "document", nullable = false, length = 20)
    private String document;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private AddressEntity addressEntity;

    @JoinColumn(name = "id_person_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonTypeEntity idPersonType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private ContactEntity contactEntity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private ClientEntity clientEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private Collection<UserEntity> userEntityCollection;

    public PersonEntity() {
    }

    public PersonEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocument() {
        return AppUtil.removeSpecialCharacters(document);
    }

    public void setDocument(String document) {
        this.document = AppUtil.removeSpecialCharacters(document);
    }

    public String getName() {
        if (name != null) {
            return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(name));
        }
        return name;
    }

    public void setName(String name) {
        this.name = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(name));
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public PersonTypeEntity getIdPersonType() {
        return idPersonType;
    }

    public void setIdPersonType(PersonTypeEntity idPersonType) {
        this.idPersonType = idPersonType;
    }

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserEntity> getUserEntityCollection() {
        return userEntityCollection;
    }

    public void setUserEntityCollection(Collection<UserEntity> userEntityCollection) {
        this.userEntityCollection = userEntityCollection;
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
        if (!(object instanceof PersonEntity)) {
            return false;
        }
        PersonEntity other = (PersonEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.PersonEntity[ id=" + id + " ]";
    }

}
