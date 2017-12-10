package com.test.android.database.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 25/11/2017.
 */

public class User {
    @SerializedName("codigo_usuario")
    public int id;
    public String username;
    public String password;
    @SerializedName("apellido_paterno")
    public String lastname;
    @SerializedName("nombre")
    public String name;
    @SerializedName("codigoempresa")
    public int enterpriseId;

    public User() {
        this.id = -1;
    }

    public User(int id, String username, String password, String lastname, String name, int enterpriseId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.enterpriseId = enterpriseId;
    }

    public User(int id, String lastname, String name) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
    }
}
