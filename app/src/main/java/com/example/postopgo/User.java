package com.example.postopgo;


public class User
{
    private String email;
    private String phone;

    public User() {}

    public User(String em, String pn)
    {
        email = em;
        phone = pn;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }
}
