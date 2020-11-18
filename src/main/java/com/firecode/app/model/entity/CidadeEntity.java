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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "cidade", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})

public class CidadeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @NotNull     
    @Column(name = "uf", length = 2,  nullable = false)
    private String uf;

    @NotNull     
    @Column(name = "codigo_ibge", nullable = false)
    private Integer codigoIbge;

    @NotNull     
    @Column(name = "ddd", length = 2, nullable = false)
    private String ddd;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCidade")
    private Collection<LogradouroEntity> logradouroEntityCollection;

    public CidadeEntity() {
    }

    public CidadeEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @XmlTransient
    public Collection<LogradouroEntity> getLogradouroEntityCollection() {
        return logradouroEntityCollection;
    }

    public void setLogradouroEntityCollection(Collection<LogradouroEntity> logradouroEntityCollection) {
        this.logradouroEntityCollection = logradouroEntityCollection;
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
        if (!(object instanceof CidadeEntity)) {
            return false;
        }
        CidadeEntity other = (CidadeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.CidadeEntity[ id=" + id + " ]";
    }

}
