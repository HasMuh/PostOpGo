package com.hasan.postopgo;


public class User
{
    public String email;
    public String phoneNumber;
    public String name;
    public String practiceName;

    public User() {}

    public User(String ln, String em, String pn, String prac)
    {
        email = em;
        phoneNumber = pn;
        name = ln;
        practiceName = prac;
    }

    public String getEmail() {return email;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getName() {return name;};

    public String getPractice() {return practiceName;};
}
