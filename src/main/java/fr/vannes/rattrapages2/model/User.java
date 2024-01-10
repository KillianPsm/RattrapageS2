package fr.vannes.rattrapages2.model;

import java.sql.Date;

public class User {
    private int id;
    private String lastname;
    private String firstname;
    private Date birthdate;
    private String className;
    private String pwd;
    private int role;

    public User() {
    }

    /**
     * @param lastname
     * @param firstname
     * @param birthdate
     * @param className
     * @param pwd
     */
    public User(String lastname, String firstname, Date birthdate, String className, String pwd) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.className = className;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthdate=" + birthdate +
                ", className='" + className + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role=" + role +
                '}';
    }
}
