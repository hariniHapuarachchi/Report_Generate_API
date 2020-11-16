package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subUser")
public class SubUser {

    private String parentId;
    @PrimaryKey
    private String userId;
    private String fName;
    private String gender;
    private String age;

    public SubUser() {
    }

    public SubUser(String userId,String parentId, String fName, String gender, String age) {
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
