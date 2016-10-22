/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author vishal
 */
public class User {

    private ObjectId id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    private boolean verified;

    private Date registerTime;
    private Date lastAcessTime;

    private int rating;
    private int reportedSpamCount;

    private char type;
    private String imgSource;

    private String[] publicPost;
    private String[] privatePost;
    private Notification notification;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastAcessTime() {
        return lastAcessTime;
    }

    public void setLastAcessTime(Date lastAcessTime) {
        this.lastAcessTime = lastAcessTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReportedSpamCount() {
        return reportedSpamCount;
    }

    public void setReportedSpamCount(int reportedSpamCount) {
        this.reportedSpamCount = reportedSpamCount;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String[] getPublicPost() {
        return publicPost;
    }

    public void setPublicPost(String[] publicPost) {
        this.publicPost = publicPost;
    }

    public String[] getPrivatePost() {
        return privatePost;
    }

    public void setPrivatePost(String[] privatePost) {
        this.privatePost = privatePost;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", verified=" + verified + ", registerTime=" + registerTime + ", lastAcessTime=" + lastAcessTime + ", rating=" + rating + ", reportedSpamCount=" + reportedSpamCount + ", type=" + type + ", imgSource=" + imgSource + ", publicPost=" + publicPost + ", privatePost=" + privatePost + ", notification=" + notification + '}';
    }

}
