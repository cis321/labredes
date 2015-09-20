package com.labredes.grupo15.lab6redes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.valores_array, android.R.layout.simple_spinner_item);
        Spinner spinner_peticiones=(Spinner) findViewById(R.id.spinner_peticiones);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        spinner_peticiones.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.btn_start);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner_peticiones=(Spinner) findViewById(R.id.spinner_peticiones);
                String text = spinner_peticiones.getSelectedItem().toString();

                if (text.equals("1")){
                    Log.i("piros","bla bla 50");
                }
                if (text.equals("10")){
                    Log.i("piros","bla bla 50");
                }
                if (text.equals("100")){
                    Log.i("piros","bla bla 100");
                }
                if (text.equals("200")){
                    Log.i("piros","bla bla 200");
                }
                if (text.equals("300")){
                    Log.i("piros","bla bla 300");
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void sendMessage(String message) {
        try {
            Socket socket = new Socket("localhost", 5006);

            OutputStream out = socket.getOutputStream();
            PrintWriter output = new PrintWriter(out);

            output.println("Hello from Android hubicación");

            out.flush();
            out.close();
            socket.close();

        } catch (UnknownHostException e){

        } catch (IOException e){

        }
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
}
