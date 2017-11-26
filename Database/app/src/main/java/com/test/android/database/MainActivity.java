package com.test.android.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import com.test.android.database.dao.UserDAO;
import com.test.android.database.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Text fields
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEnterprise;
    //Buttons
    private Button buttonSave;
    private Button buttonDelete;
    private Button buttonSearch;
    private Button buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setObjects();
    }

    private void setObjects() {
        //Set text fields
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEnterprise = findViewById(R.id.editTextEnterprise);
        //Set buttons
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonList = findViewById(R.id.buttonList);
        //Set click listener
        findViewById(R.id.buttonSave).setOnClickListener(new HandleClick());
        findViewById(R.id.buttonDelete).setOnClickListener(new HandleClick());
        findViewById(R.id.buttonSearch).setOnClickListener(new HandleClick());
        findViewById(R.id.buttonList).setOnClickListener(new HandleClick());
    }

    private void setVariables() {

    }

    private class HandleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSave:
                    add(v.getContext());
                    break;
                case R.id.buttonDelete:
                    delete(v.getContext());
                    break;
                case R.id.buttonSearch:
                    get(v.getContext());
                    break;
                case R.id.buttonList:
                    list(v.getContext());
                    break;
            }
        }
    }

    private void add(Context context) {
        Log.i("TAG","add");
        UserDAO userDAO = new UserDAO(context);
        User user = new User();
        user.id = Integer.valueOf(editTextId.getText().toString());
        user.username = editTextUsername.getText().toString();
        user.password = editTextPassword.getText().toString();
        user.name = editTextName.getText().toString();
        user.lastname = editTextLastName.getText().toString();
        user.enterpriseId = Integer.valueOf(editTextEnterprise.getText().toString());
        userDAO.insert(user);
        Toast toast = Toast.makeText(context,"Se agrego el usuario", Toast.LENGTH_LONG);
        toast.show();
    }

    private void get(Context context) {
        Log.i("TAG","get");
        UserDAO userDao = new UserDAO(context);
        int userid = Integer.valueOf(editTextId.getText().toString());
        User user = userDao.get(userid);
        editTextId.setText(String.valueOf(user.id));
        editTextUsername.setText(user.username);
        editTextPassword.setText(user.password);
        editTextName.setText(user.name);
        editTextLastName.setText(user.lastname);
        editTextEnterprise.setText(String.valueOf(user.enterpriseId));
    }

    private void delete(Context context) {
        Log.i("TAG","delete");

    }

    private void list(Context context) {
        Log.i("TAG","list");

        UserDAO userDao = new UserDAO(context);
        ArrayList<User> userList = userDao.list();
        /*for(User user:userList){

        }*/
        /* Error con gson
        Gson gson = new Gson();
        String listaJsonUsuario = gson.toJson(userList);
        Log.i("USER",listaJsonUsuario);*/
    }

}
