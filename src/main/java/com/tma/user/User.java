package com.tma.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tma.core.BaseEntity;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;


@Entity
public class User extends BaseEntity {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String firstName;
    private String lastName;
    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
//    @Type(type = "string-array")
//    @Column(
//            name = "roles",
//            columnDefinition = "text[]"
//    )
    @Transient
    private List<String> roles;

    protected User(){
        super();
    }

    public User(String userName, String firstName, String lastName, String password, List<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        setPassword(password);
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getPassword() {
        return password;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
