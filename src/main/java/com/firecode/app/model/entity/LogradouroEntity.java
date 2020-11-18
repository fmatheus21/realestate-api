package com.firecode.app.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "logradouro", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"cep"})})

public class LogradouroEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "cep", nullable = false, length = 11)
    private String cep;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Basic(optional = false)
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "descricao_sem_numero", length = 100)
    private String descricaoSemNumero;

    @Column(name = "descricao_cidade", length = 100)
    private String descricaoCidade;

    @Column(name = "codigo_cidade_ibge")
    private Integer codigoCidadeIbge;

    @Column(name = "descricao_bairro", length = 100)
    private String descricaoBairro;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLogradouro")
    private Collection<EnderecoEntity> enderecoEntityCollection;

    @JoinColumn(name = "id_cidade", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private CidadeEntity idCidade;

    public LogradouroEntity() {
    }

    public LogradouroEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDescricaoSemNumero() {
        return descricaoSemNumero;
    }

    public void setDescricaoSemNumero(String descricaoSemNumero) {
        this.descricaoSemNumero = descricaoSemNumero;
    }

    public String getDescricaoCidade() {
        return descricaoCidade;
    }

    public void setDescricaoCidade(String descricaoCidade) {
        this.descricaoCidade = descricaoCidade;
    }

    public Integer getCodigoCidadeIbge() {
        return codigoCidadeIbge;
    }

    public void setCodigoCidadeIbge(Integer codigoCidadeIbge) {
        this.codigoCidadeIbge = codigoCidadeIbge;
    }

    public String getDescricaoBairro() {
        return descricaoBairro;
    }

    public void setDescricaoBairro(String descricaoBairro) {
        this.descricaoBairro = descricaoBairro;
    }

    @XmlTransient
    public Collection<EnderecoEntity> getEnderecoEntityCollection() {
        return enderecoEntityCollection;
    }

    public void setEnderecoEntityCollection(Collection<EnderecoEntity> enderecoEntityCollection) {
        this.enderecoEntityCollection = enderecoEntityCollection;
    }

    public CidadeEntity getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(CidadeEntity idCidade) {
        this.idCidade = idCidade;
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
        if (!(object instanceof LogradouroEntity)) {
            return false;
        }
        LogradouroEntity other = (LogradouroEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.LogradouroEntity[ id=" + id + " ]";
    }

}
