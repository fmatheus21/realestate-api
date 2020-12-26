package com.firecode.app.model.entity;

import com.firecode.app.controller.util.AppUtil;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "user", catalog = "realestate", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_person"}),
    @UniqueConstraint(columnNames = {"avatar"}),
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"username"})})

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 12)
    @Column(name = "password", nullable = false, length = 70)
    private String password;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "avatar", nullable = false, length = 30)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUser")
    private UserPermissionJoinEntity userPermissionJoinEntity;

    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonEntity idPerson;

    @JoinColumn(name = "id_user_status", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private UserStatusEntity userStatusEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission_join", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private List<PermissionEntity> permissions;

    public UserEntity() {
    }

    public UserEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        if (username != null) {
            return AppUtil.convertAllLowercaseCharacters(username);
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = AppUtil.convertAllUppercaseCharacters(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserPermissionJoinEntity getUserPermissionJoinEntity() {
        return userPermissionJoinEntity;
    }

    public void setUserPermissionJoinEntity(UserPermissionJoinEntity userPermissionJoinEntity) {
        this.userPermissionJoinEntity = userPermissionJoinEntity;
    }

    public PersonEntity getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(PersonEntity idPerson) {
        this.idPerson = idPerson;
    }

    public UserStatusEntity getUserStatusEntity() {
        return userStatusEntity;
    }

    public void setUserStatusEntity(UserStatusEntity userStatusEntity) {
        this.userStatusEntity = userStatusEntity;
    }

    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.UserEntity[ id=" + id + " ]";
    }

}
