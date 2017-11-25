package com.test.android.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText etxtSaldoInicial;
    private EditText etxtSaldoFinal;
    private TextView txtvMovimient;

    private int MONTO_INICIAL;
    private int SALDO_DISPONIBLE;
    private String MOVIMIENTO = "";

    private static final String TAG  = "MovBancario";
    private static Semaphore semaphoreMovBancario = new Semaphore(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarVariable();
        inicalizarObjeto();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void test(){
        System.out.println("Thread test");

    }

    private void inicializarVariable() {
        etxtSaldoInicial = (EditText) findViewById(R.id.editText3);
        etxtSaldoFinal = (EditText) findViewById(R.id.editText4);
        txtvMovimient = (TextView) findViewById(R.id.textView5);
        etxtSaldoInicial.setText("");
        etxtSaldoFinal.setText("");
        SALDO_DISPONIBLE = 1000;
        MONTO_INICIAL = 1000;
        findViewById(R.id.process_btn).setOnClickListener(new HandleClick());
    }

    private void inicalizarObjeto() {

    }

    private class HandleClick implements View.OnClickListener {
        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.process_btn:
                    for(int i=0; i<100; i++) {
                        int montoARetirar = (int) (Math.random()*100) + 1;
                        MovimientoBancario movimientoBancario = new MovimientoBancario(montoARetirar,i);
                        //movimientoBancario.run();
                        Thread hiloMovimientoBancario = new Thread(movimientoBancario);
                        hiloMovimientoBancario.start();
                        //runOnUiThread(hiloMovimientoBancario);
                    }
            }
        }
    }

    public class MovimientoBancario implements Runnable {
        private static final String TAG = "HiloMovBancario";

        private int montoARetirar;
        private int orden;

        public MovimientoBancario(int montoARetirar, int orden) {
            this.montoARetirar = montoARetirar;
            this.orden = orden;
        }

        public void run(){
            try {
                semaphoreMovBancario.acquire();
                Log.i(TAG, "run: Orden " + orden + "--> Monto a retirar:" + montoARetirar);
                if (SALDO_DISPONIBLE >= montoARetirar) {
                    SALDO_DISPONIBLE = SALDO_DISPONIBLE - montoARetirar;
                    Log.i(TAG, "run: Orden: " + orden + "--> retiro" + montoARetirar + "--> SALDO DISPONIBLE" + SALDO_DISPONIBLE);
                } else {
                    Log.i(TAG, "run: Orden: " + orden + "--> SALDO INSUFICIENTE: " + SALDO_DISPONIBLE);
                }
                MOVIMIENTO = MOVIMIENTO + (char)13 + (char)10 +"Orden: " + orden + " Retiro: " + montoARetirar + " Saldo: " + SALDO_DISPONIBLE;

            } catch (InterruptedException e) {
                e.printStackTrace();
                semaphoreMovBancario.release();
            } finally {
                runOnUiThread(new Runnable() {
                    public void run() {
                        txtvMovimient.setText(MOVIMIENTO);
                        etxtSaldoFinal.setText(String.valueOf(SALDO_DISPONIBLE));
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (Exception e) {
                            System.out.println("ASD");
                        }
                    }
                });
                semaphoreMovBancario.release();
            }


        }
    }
}
