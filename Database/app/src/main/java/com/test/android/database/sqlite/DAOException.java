package com.test.android.database.sqlite;

import android.database.sqlite.SQLiteException;

/**
 * Created by Android on 25/11/2017.
 */

public class DAOException extends SQLiteException {
    public DAOException(){
        super();
    }

    public DAOException(String error) {
        super(error);
    }

    public DAOException(String error, Throwable cause) {
        super(error, cause);
    }
}
