package com.firecode.app.model.entity;

import com.firecode.app.controller.util.AppUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "permissao", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"nome"})})

public class PermissaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 45)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPermissao")
    private Collection<UsuarioMapPermissaoEntity> usuarioMapPermissaoEntityCollection;

    public PermissaoEntity() {
    }

    public PermissaoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        if (nome != null) {
            return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(nome));
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<UsuarioMapPermissaoEntity> getUsuarioMapPermissaoEntityCollection() {
        return usuarioMapPermissaoEntityCollection;
    }

    public void setUsuarioMapPermissaoEntityCollection(Collection<UsuarioMapPermissaoEntity> usuarioMapPermissaoEntityCollection) {
        this.usuarioMapPermissaoEntityCollection = usuarioMapPermissaoEntityCollection;
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
        if (!(object instanceof PermissaoEntity)) {
            return false;
        }
        PermissaoEntity other = (PermissaoEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.PermissaoEntity[ id=" + id + " ]";
    }

}
