package com.example.postopgo;


public class User
{
    public String email;
    public String phone;
    public String name;

    public User() {}

    public User(String ln, String em, String pn)
    {
        email = em;
        phone = pn;
        name = ln;
    }

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    public String getName() {return name;};
}
