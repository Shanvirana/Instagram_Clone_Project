package com.example.dell.instagram_clone.Model;

/**
 * Created by Dell on 21-07-2018.
 */

public class Users {
    String useremail,username,password,email,gender;
    public Users(String useremail,String username,String gender)
    {
        this.useremail = useremail;
        this.username = username;
        this.gender = gender;
    }
    public  void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getEmail()
    {
        return email;
    }
    public String getGender()
    {
        return gender;
    }
    public String toString()
    {
        return this.getUsername();
    }



}
