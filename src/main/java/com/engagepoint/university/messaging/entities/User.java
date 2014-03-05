package com.engagepoint.university.messaging.entities;

import com.engagepoint.university.messaging.entities.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS, query = "SELECT us FROM User us"),
        @NamedQuery(name = User.GET_ALL_BY_USER_ID, query = "SELECT us FROM User us WHERE us.id = :idUser"),
        @NamedQuery(name = User.GET_ALL_BY_USER_NAME, query = "SELECT us FROM User us WHERE us.name = :name"),
        @NamedQuery(name = User.GET_ALL_BY_USER_EMAIL, query = "SELECT us FROM User us WHERE us.email = :email"),
        @NamedQuery(name = User.GET_ALL_BY_USER_PHONE_NUMBER, query = "SELECT us FROM User us WHERE us.phoneNumber = :phoneNumber")})

public class User implements Serializable, BaseEntity {

    private static final long serialVersionUID = 765348739781231295L;
    public static final String GET_ALL_USERS = "User.findAll";
    public static final String GET_ALL_BY_USER_ID = "User.findByIdUser";
    public static final String GET_ALL_BY_USER_NAME = "User.findByName";
    public static final String GET_ALL_BY_USER_EMAIL = "User.findByEmail";
    public static final String GET_ALL_BY_USER_PHONE_NUMBER = "User.findByPhoneNumber";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_email", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "email_id", referencedColumnName = "id")})
    private Collection<Email> emailCollection;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sms", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "sms_id", referencedColumnName = "id")})
    private Collection<Sms> smsCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}