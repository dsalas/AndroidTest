package com.test.android.listspermisions;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private ListView productListView;
    String[] from;
    int[] to;
    ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();
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
                        //Add products
                        addProduct();
                    }
                }
                return false;
            }

        });
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        input.setOnFocusChangeListener(ofcListener);
        input.requestFocus();

        productListView = (ListView) findViewById(R.id.listView);
        setAddProduct(R.layout.product_adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {
                Log.i("CLICK","Click listener");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Desea elminar el producto " + productList.get(position).get("product"));
                builder.setTitle("Eliminar");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productList.remove(position);
                        productListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        productListView.setOnItemClickListener(listener);
    }

    private class MyFocusChangeListener implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.d("FOCUS","Focus change");
            if (v.getId() == R.id.editText) {
                if (!hasFocus) {
                    Log.i("FOCUS", "onFocusChange: relocate");
                    input.post(new Runnable() {
                        @Override
                        public void run() {
                            input.requestFocus();
                        }
                    });
                }
            } else {
                Log.i("FOCUS", "onFocusChange: no action");
            }
        }
    }

    private void setAddProduct(Integer xlayout){
        from = new String[]{"product","start_time"};
        to = new int[] {R.id.textView1, R.id.textView2};
        productListAdapter = new SimpleAdapter(this, productList, xlayout, from, to);
        if (productListView == null) {
            productListView = (ListView) findViewById(R.id.listView);
        }
        productListView.setAdapter(productListAdapter);

    }

    private void addProduct(){
        String product_name = input.getText().toString();
        if (product_name.equals("")) {
            return;
        }
        if (containsProduct(product_name)) {
            Toast toast = Toast.makeText(this, "El producto ya existe.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        input.setText("");
        HashMap<String,String> product = new HashMap<String, String>();
        product.put("product", product_name);
        product.put("start_time", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        productList.add(product);
        Log.i("PRODUCT","Adding "+product_name);
        productListAdapter.notifyDataSetChanged();
    }

    private boolean containsProduct(String product_name){
        Iterator<HashMap<String,String>> iterator = productList.iterator();
        while (iterator.hasNext()) {
            HashMap<String,String> product = iterator.next();
            if (product.containsValue(product_name)) {
                return true;
            }
        }
        return false;
    }
}
