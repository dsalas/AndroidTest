package com.test.android.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Text fields
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmpresa;
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
        editTextEmpresa = findViewById(R.id.editTextEmpresa);
        //Set buttons
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonList = findViewById(R.id.buttonList);
    }

    private void setVariables() {

    }
}
