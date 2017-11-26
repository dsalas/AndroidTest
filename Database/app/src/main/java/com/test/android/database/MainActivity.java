package com.test.android.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
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
    }

    private void get(Context context) {
        Log.i("TAG","get");

    }

    private void delete(Context context) {
        Log.i("TAG","delete");

    }

    private void list(Context context) {
        Log.i("TAG","list");

    }

}
