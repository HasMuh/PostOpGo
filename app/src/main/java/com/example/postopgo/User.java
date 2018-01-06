package com.example.postopgo;


public class User
{
    public String email;
    public String phoneNumber;
    public String name;

    public User() {}

    public User(String ln, String em, String pn)
    {
        email = em;
        phoneNumber = pn;
        name = ln;
    }

    public String getEmail() {return email;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getName() {return name;};
}
