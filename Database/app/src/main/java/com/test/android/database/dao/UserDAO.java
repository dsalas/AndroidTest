package com.test.android.database.dao;

import com.test.android.database.entity.User;
import com.test.android.database.sqlite.Contract;
import com.test.android.database.sqlite.DAOException;
import com.test.android.database.sqlite.DBHelper;
import com.test.android.database.sqlite.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by Android on 25/11/2017.
 */

public class UserDAO {
    private DBHelper _dBHelper;
    private static final String TAG = "UsuarioDAO";

    public UserDAO(Context context) {
        _dBHelper = new DBHelper(context);
        //SQLiteDatabase db = _dBHelper.getReadableDatabase();
    }

    public long insert(User user) {
        Log.i(TAG, "Insert on userDAO");
        SQLiteDatabase db = _dBHelper.getWritableDatabase();
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(Contract.User.userId, user.id);
            values.put(Contract.User.password, user.password);
            values.put(Contract.User.lastname, user.lastname);
            values.put(Contract.User.name, user.name);
            values.put(Contract.User.enterpriseId, user.enterpriseId);
            values.put(Contract.User.username, user.username);

            result = db.insert(DataSource.USER_TABLE, null, values);
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return result;
    }

    public User get(int guserid) throws DAOException {
        Log.i(TAG, "UsuarioDAO: get");
        SQLiteDatabase db = _dBHelper.getReadableDatabase();
        User model = new User();
        String[] projection = {Contract.User.userId,
                Contract.User.username,
                Contract.User.password,
                Contract.User.name,
                Contract.User.lastname,
                Contract.User.enterpriseId};
        String selection = Contract.User.userId + " = ? ";
        String[] selectionArgs = {String.valueOf(guserid)};
        String sortOrder = "";
        try {
            Cursor c = db.query(DataSource.USER_TABLE,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
            if (c.getCount() > 0) {
                c.moveToFirst();
                int userid = c.getInt(c.getColumnIndexOrThrow(Contract.User.userId));
                String username = c.getString(c.getColumnIndexOrThrow(Contract.User.username));
                String password = c.getString(c.getColumnIndexOrThrow(Contract.User.password));
                String name = c.getString(c.getColumnIndexOrThrow(Contract.User.name));
                String lastname = c.getString(c.getColumnIndexOrThrow(Contract.User.lastname));
                int enterpriseId = c.getInt(c.getColumnIndexOrThrow(Contract.User.enterpriseId));
                model = new User(userid, username, password, lastname, name, enterpriseId);
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UserDAO: get failed.");
        } finally {
            db.close();
        }
        return model;
    }

    public ArrayList<User> list(int guserid) throws DAOException {
        Log.i(TAG, "UsuarioDAO: get");
        SQLiteDatabase db = _dBHelper.getReadableDatabase();
        ArrayList<User> model = new ArrayList<User>();
        String[] projection = {Contract.User.userId,
                Contract.User.username,
                Contract.User.password,
                Contract.User.name,
                Contract.User.lastname,
                Contract.User.enterpriseId};
        //String selection = "";
        //String[] selectionArgs = {};
        String sortOrder = "";
        try {
            Cursor c = db.query(DataSource.USER_TABLE,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int userid = c.getInt(c.getColumnIndexOrThrow(Contract.User.userId));
                    String username = c.getString(c.getColumnIndexOrThrow(Contract.User.username));
                    String password = c.getString(c.getColumnIndexOrThrow(Contract.User.password));
                    String name = c.getString(c.getColumnIndexOrThrow(Contract.User.name));
                    String lastname = c.getString(c.getColumnIndexOrThrow(Contract.User.lastname));
                    int enterpriseId = c.getInt(c.getColumnIndexOrThrow(Contract.User.enterpriseId));
                    model.add(new User(userid, username, password, lastname, name, enterpriseId));
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UserDAO: list failed.");
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return model;
    }

}
