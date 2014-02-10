package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entities.baseentity.Base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Alexey on 2/9/14.
 */

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT us FROM User us"),
        @NamedQuery(name = User.GET_ALL_BY_USER_ID, query = "SELECT us FROM User us WHERE us.idUser = :idUser"),
        @NamedQuery(name = User.GET_ALL_BY_USER_NAME, query = "SELECT us FROM User us WHERE us.name = :name"),
        @NamedQuery(name = User.GET_ALL_BY_USER_EMAIL, query = "SELECT us FROM User us WHERE us.email = :email"),
        @NamedQuery(name = User.GET_ALL_BY_USER_PHONE_NUMBER, query = "SELECT us FROM User us WHERE us.phoneNumber = :phoneNumber")})

public class User extends Base implements Serializable {

    private static final long serialVersionUID = 1345678798781231239L;
    public static final String GET_ALL = "User.findAll";
    public static final String GET_ALL_BY_USER_ID = "User.findByIdUser";
    public static final String GET_ALL_BY_USER_NAME = "User.findByName";
    public static final String GET_ALL_BY_USER_EMAIL = "User.findByEmail";
    public static final String GET_ALL_BY_USER_PHONE_NUMBER = "User.findByPhoneNumber";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "userCollection")
    private Collection<Email> emailCollection;

    @ManyToMany(mappedBy = "userCollection")
    private Collection<Sms> smsCollection;

    public User() {
    }

    public User(UserDTO newUser) {
        this.name = newUser.getName();
        this.email = newUser.getEmail();
        this.phoneNumber = newUser.getPhoneNumber();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }

    public Collection<Sms> getSmsCollection() {
        return smsCollection;
    }

    public void setSmsCollection(Collection<Sms> smsCollection) {
        this.smsCollection = smsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ idUser=" + idUser + " ]";
    }

}
