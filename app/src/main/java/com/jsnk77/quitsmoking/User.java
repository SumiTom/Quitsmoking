package com.jsnk77.quitsmoking;

/**
 * Created by jsnk77 on 14/12/13.
 */
public class  User {

    public String id;
    public String name;
    public String age;
    public String gender;
    public String fbId;


    public User(){
        this.id = id;
        this.age=age;
        this.name=name;
        this.gender=gender;
        this.fbId=fbId;
    }


    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
