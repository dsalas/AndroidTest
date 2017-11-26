package com.test.android.database.sqlite;

/**
 * Created by Android on 25/11/2017.
 */

public class DataSource {
    public static final String USER_TABLE = "user";

    public static final String STRING_TYPE ="text";
    public static final String INT_TYPE ="integer";
    public static final String NUMERIC_TYPE ="real";

    public static final String CREATE_USER = "create table IF NOT EXITS " + USER_TABLE + "(" +
            Contract.User.userId + " " + INT_TYPE + " primary key," +
            Contract.User.username + " " + STRING_TYPE + "not null,"+
            Contract.User.name + " " + STRING_TYPE + "not null,"+
            Contract.User.password + " " + STRING_TYPE + "not null,"+
            Contract.User.lastname + " " + STRING_TYPE + "not null,"+
            Contract.User.enterpriseId + " " + INT_TYPE + "not null)";

}
