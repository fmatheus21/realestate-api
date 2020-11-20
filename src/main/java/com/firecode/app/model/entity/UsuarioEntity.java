package com.firecode.app.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "usuario", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario"}),
    @UniqueConstraint(columnNames = {"id"})})

public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "usuario", nullable = false, length = 45)
    private String usuario;

    @Basic(optional = false)
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Basic(optional = false)
    @Column(name = "avatar", nullable = false, length = 30)
    private String avatar;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCadastro", fetch = FetchType.LAZY)
    private Collection<ClienteEntity> clienteEntityCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioAtualizacao", fetch = FetchType.LAZY)
    private Collection<ClienteEntity> clienteEntityCollection1;

    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PessoaEntity idPessoa;

    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private UsuarioStatusEntity idStatus;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private UsuarioMapPermissaoEntity usuarioMapPermissaoEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_map_permissao", joinColumns = @JoinColumn(name = "id_usuario"),
             inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private List<PermissaoEntity> permissoes;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @XmlTransient
    public Collection<ClienteEntity> getClienteEntityCollection() {
        return clienteEntityCollection;
    }

    public void setClienteEntityCollection(Collection<ClienteEntity> clienteEntityCollection) {
        this.clienteEntityCollection = clienteEntityCollection;
    }

    @XmlTransient
    public Collection<ClienteEntity> getClienteEntityCollection1() {
        return clienteEntityCollection1;
    }

    public void setClienteEntityCollection1(Collection<ClienteEntity> clienteEntityCollection1) {
        this.clienteEntityCollection1 = clienteEntityCollection1;
    }

    public PessoaEntity getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(PessoaEntity idPessoa) {
        this.idPessoa = idPessoa;
    }

    public UsuarioStatusEntity getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(UsuarioStatusEntity idStatus) {
        this.idStatus = idStatus;
    }

    public UsuarioMapPermissaoEntity getUsuarioMapPermissaoEntity() {
        return usuarioMapPermissaoEntity;
    }

    public void setUsuarioMapPermissaoEntity(UsuarioMapPermissaoEntity usuarioMapPermissaoEntity) {
        this.usuarioMapPermissaoEntity = usuarioMapPermissaoEntity;
    }

    public List<PermissaoEntity> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoEntity> permissoes) {
        this.permissoes = permissoes;
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
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.UsuarioEntity[ id=" + id + " ]";
    }

}
