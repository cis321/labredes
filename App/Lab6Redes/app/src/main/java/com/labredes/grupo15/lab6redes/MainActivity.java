package com.labredes.grupo15.lab6redes;

import android.location.Location;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String latitude;
    private String longitude;

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

        buildGoogleApiClient();
        locationService();

        spinner_peticiones.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.btn_start);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner_peticiones=(Spinner) findViewById(R.id.spinner_peticiones);
                String text = spinner_peticiones.getSelectedItem().toString();

                if (text.equals("1")){

                    Log.i("blaa", "" + mLastLocation );
                    //sendMessageTCP("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + "Longitud: " + String.valueOf(mLastLocation.getLongitude()), 1);
                }
                if (text.equals("10")){
                    sendMessageTCP("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + "Longitud: " + String.valueOf(mLastLocation.getLongitude()), 10);
                }
                if (text.equals("100")){
                    sendMessageTCP("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + "Longitud: " + String.valueOf(mLastLocation.getLongitude()), 100);
                }
                if (text.equals("200")){
                    sendMessageTCP("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + "Longitud: " + String.valueOf(mLastLocation.getLongitude()), 200);
                }
                if (text.equals("300")){
                    sendMessageTCP("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + "Longitud: " + String.valueOf(mLastLocation.getLongitude()), 300);
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

    public void sendMessageTCP(String message, int repeticiones) {
        for( int i = 0 ; i < repeticiones ; i++) {
            try {
                Socket socket = new Socket("192.168.0.7", 5006);

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                PrintWriter output = new PrintWriter(out);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                output.println("HELLO\n");


                if (reader.readLine().equals("HELLO\n")){
                    output.println(message);
                }
                output.println("GOODBYE\n");

                out.flush();
                out.close();
                socket.close();

            } catch (UnknownHostException e){

            } catch (IOException e){

            }
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

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void locationService(){

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

            latitude = String.valueOf(mLastLocation.getLatitude());
            longitude = String.valueOf(mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
    }

    @Override
    public void onDisconnected() {
    }

    @Override
    public void onConnectionFailed(ConnectionResult var1) {
    }

}
