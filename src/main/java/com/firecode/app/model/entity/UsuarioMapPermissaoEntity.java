package com.firecode.app.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "usuario_map_permissao", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_usuario"}),
    @UniqueConstraint(columnNames = {"id"})})

public class UsuarioMapPermissaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "id_permissao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PermissaoEntity idPermissao;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private UsuarioEntity idUsuario;

    public UsuarioMapPermissaoEntity() {
    }

    public UsuarioMapPermissaoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PermissaoEntity getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(PermissaoEntity idPermissao) {
        this.idPermissao = idPermissao;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof UsuarioMapPermissaoEntity)) {
            return false;
        }
        UsuarioMapPermissaoEntity other = (UsuarioMapPermissaoEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.UsuarioMapPermissaoEntity[ id=" + id + " ]";
    }
    
}
