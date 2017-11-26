package com.test.android.database.entity;

/**
 * Created by Android on 25/11/2017.
 */

public class User {
    public int id;
    public String username;
    public String password;
    public String lastname;
    public String name;
    public int enterpriseId;

    public User() {
    }

    public User(int id, String username, String password, String lastname, String name, int enterpriseId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.enterpriseId = enterpriseId;
    }
}
