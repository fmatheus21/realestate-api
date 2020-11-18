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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pessoa", catalog = "imobiliaria", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"cpf_cnpj"})})

public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @NotBlank
    @Column(name = "nome_razaosocial", nullable = false, length = 70)
    private String nomeRazaosocial;

    @Basic(optional = false)
    @NotNull
    @NotBlank
    @Column(name = "cpf_cnpj", nullable = false, length = 20)
    private String cpfCnpj;
 
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoa",fetch = FetchType.LAZY)
    private ClienteEntity clienteEntity;

    @JoinColumn(name = "id_tipo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PessoaTipoEntity idTipo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPessoa", fetch = FetchType.LAZY)
    private EnderecoEntity enderecoEntity;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoa", fetch = FetchType.LAZY)
    private Collection<UsuarioEntity> usuarioEntityCollection;

    public PessoaEntity() {   
    }

    public PessoaEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRazaosocial() {
        if (nomeRazaosocial != null) {
            return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(nomeRazaosocial));
        }
        return nomeRazaosocial;
    }

    public void setNomeRazaosocial(String nomeRazaosocial) {
        this.nomeRazaosocial = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(nomeRazaosocial));
    }

    public String getCpfCnpj() {
        if (cpfCnpj != null) {
            if (idTipo.getId() == 1) {
                return AppUtil.formatCPF(cpfCnpj);
            } else if (idTipo.getId() == 2) {
                return AppUtil.formatCNPJ(cpfCnpj);
            }
        }
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = AppUtil.removeSpecialCharacters(cpfCnpj);
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public PessoaTipoEntity getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(PessoaTipoEntity idTipo) {
        this.idTipo = idTipo;
    }

    public EnderecoEntity getEnderecoEntity() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }

    @XmlTransient
    public Collection<UsuarioEntity> getUsuarioEntityCollection() {
        return usuarioEntityCollection;
    }

    public void setUsuarioEntityCollection(Collection<UsuarioEntity> usuarioEntityCollection) {
        this.usuarioEntityCollection = usuarioEntityCollection;
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
        if (!(object instanceof PessoaEntity)) {
            return false;
        }
        PessoaEntity other = (PessoaEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.PessoaEntity[ id=" + id + " ]";
    }

}
