package com.BudgetPlanner.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;


    public AppUser() {
    }

    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AppUser(String userName, String password, Client client) {
        this.userName = userName;
        this.password = password;
        this.client = client;
      //  this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    public Collection<Role> getRoles() {
//        return roles;
//    }

//    public void setRoles(Collection<Role> roles) {
//        this.roles = roles;
//    }
}
