package com.test.android.listspermisions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button initProces;
    private ListView productListView;
    String[] from;
    int[] to;
    ArrayList<HashMap<String, String>> productList;
    SimpleAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setObjects();
    }

    private void setObjects(){
        input = findViewById(R.id.editText);
        input.setOnKeyListener( new View.OnKeyListener() {
            public boolean onKey( View arg0, int arg1, KeyEvent arg2) {
                final View view = arg0;
                if (arg2.getAction() == KeyEvent.ACTION_UP) {
                    if (arg1 == KeyEvent.KEYCODE_ENTER) {
                        Log.i("DB", "onKey: Enter");
                        //Add product
                    }
                }
                return false;
            }

        });
    }
}
