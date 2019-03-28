package org.intranet.entity;

import javax.persistence.*;

@Entity(name = "USERS")
public class User extends ParentEntity {
    private String firstName;
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    private boolean enabled;

    @OneToOne(cascade = {CascadeType.ALL})
    private Authority authority;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        authority.setUsername(this.username);
        this.authority = authority;
    }
}
