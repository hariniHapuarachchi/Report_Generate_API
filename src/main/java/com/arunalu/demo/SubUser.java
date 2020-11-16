package com.arunalu.demo;

public class SubUser {

    private String parentId;
    private String userId;
    private String fName;
    private String gender;
    private String age;

    public SubUser() {
    }

    public SubUser(String parentId, String userId, String fName, String gender, String age) {
        this.parentId = parentId;
        this.userId = userId;
        this.fName = fName;
        this.gender = gender;
        this.age = age;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "SubUser{" +
                "parentId='" + parentId + '\'' +
                ", userId='" + userId + '\'' +
                ", fName='" + fName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
