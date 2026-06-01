package com.example.Model;

public class User {
    private int id;
    private String fullName;
    private String email;
    private int age;
    private String gender;
    private String major;

    public User() {
    }

    public User(int id, String fullName, String email, int age, String gender, String major) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.major = major;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}