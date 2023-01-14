package com.driver.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // can keep here
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastname;

    public User(String username, String password, String firstName, String lastname, List<Blog> blogsWritten) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastname = lastname;
        this.blogsWritten = blogsWritten;
    }

    public User() {
    }

    public int getId() {
        return id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Blog> getBlogsWritten() {
        return blogsWritten;
    }

    public void setBlogsWritten(List<Blog> blogsWritten) {
        this.blogsWritten = blogsWritten;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogsWritten;


}