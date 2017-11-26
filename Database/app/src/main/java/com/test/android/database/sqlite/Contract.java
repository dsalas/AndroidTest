package com.test.android.database.sqlite;

/**
 * Created by Android on 25/11/2017.
 */

public class Contract {

    interface  UserColumn {
        String userId = "userid";
        String username = "username";
        String password = "password";
        String lastname = "lastname";
        String name = "name";
        String enterpriseId = "enterpriseId";
    }

    public static class User implements UserColumn {

    }
}
