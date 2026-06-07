package com.royal.bean;

import java.util.Arrays;

public class StudentBean {

    private Integer id;
    private String fullname;
    private Integer age;
    private String course;
    private String gender;
    private String[] hobby;
    private String dob;
    private String email;
    private String mobile;
    private String address;

    public StudentBean() {
    }

    // ================= FULL CONSTRUCTOR =================
    public StudentBean(Integer id, String fullname, Integer age, String course,
                       String gender, String[] hobby, String dob,
                       String email, String mobile, String address) {

        this.id = id;
        this.fullname = fullname;
        this.age = age;
        this.course = course;
        this.gender = gender;
        this.hobby = hobby;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    // ================= WITHOUT ID =================
    public StudentBean(String fullname, Integer age, String course,
                       String gender, String[] hobby, String dob,
                       String email, String mobile, String address) {

        this.fullname = fullname;
        this.age = age;
        this.course = course;
        this.gender = gender;
        this.hobby = hobby;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    // ================= GETTERS & SETTERS =================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // CHECKBOX SELECT METHOD

    public boolean isAvailableHobby(String value) {

        if (hobby == null || value == null) {
            return false;
        }

        for (String h : hobby) {
            if (value.equalsIgnoreCase(h)) {
                return true;
            }
        }

        return false;
    }

    // HOBBY STRING FOR DB

    public String getHobbiesStr() {

        if (hobby == null || hobby.length == 0) {
            return "";
        }

        return String.join(",", hobby);
    }

    // SET HOBBY FROM DB STRING

    public void setHobbyFromString(String hobbyStr) {

        if (hobbyStr != null && !hobbyStr.issEmpty()) {
            this.hobby = hobbyStr.split(",");
        }
    }

    @Override
    public String toString() {
        return "StudentBean [id=" + id +
                ", fullname=" + fullname +
                ", age=" + age +
                ", course=" + course +
                ", gender=" + gender +
                ", hobby=" + Arrays.toString(hobby) +
                ", dob=" + dob +
                ", email=" + email +
                ", mobile=" + mobile +
                ", address=" + address + "]";
    }
}zs
