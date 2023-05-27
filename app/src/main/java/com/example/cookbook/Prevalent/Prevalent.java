package com.example.cookbook.Prevalent;

import com.example.cookbook.User;
import com.google.firebase.database.DatabaseReference;

public class Prevalent {
    public static User currentOnlineUser;

    public static final String UserPhoneKey = "UserPhone";
    public static final String UserPasswordKey = "UserPassword";
    public static DatabaseReference currentOnLineUser;
}
