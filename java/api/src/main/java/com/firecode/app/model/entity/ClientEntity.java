package com.firecode.app.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "client", catalog = "realestate", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_person"}),
    @UniqueConstraint(columnNames = {"id"})})

public class ClientEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private PersonEntity idPerson;

    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PlanEntity idPlan;

    @Basic(optional = false)
    @Lob
    @Column(name = "filter", nullable = false, length = 2147483647)
    private String filter;

    public ClientEntity() {
    }

    public ClientEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PersonEntity getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(PersonEntity idPerson) {
        this.idPerson = idPerson;
    }

    public PlanEntity getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanEntity idPlan) {
        this.idPlan = idPlan;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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
        if (!(object instanceof ClientEntity)) {
            return false;
        }
        ClientEntity other = (ClientEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.ClientEntity[ id=" + id + " ]";
    }

}
