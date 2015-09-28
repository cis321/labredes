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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

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

        spinner_peticiones.setAdapter(adapter);

        LocartorService locartorService = new LocartorService(getApplicationContext());

        Log.i("locartorService: ","" + locartorService);

        mLastLocation = locartorService.getLocationObject();

        buffer.setLocation(mLastLocation);

        Button button = (Button) findViewById(R.id.btn_start);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner_peticiones=(Spinner) findViewById(R.id.spinner_peticiones);
                String text = spinner_peticiones.getSelectedItem().toString();

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                if (radioButton.getText().equals("TCP")){

                    if (text.equals("1")){
                        new SenderTCP().execute(1);
                    }
                    if (text.equals("10")){
                        new SenderTCP().execute(10);
                    }
                    if (text.equals("50")){
                        new SenderTCP().execute(50);
                    }
                    if (text.equals("100")){
                        new SenderTCP().execute(100);
                    }
                    if (text.equals("200")){
                        new SenderTCP().execute(200);
                    }
                    if (text.equals("300")){
                        new SenderTCP().execute(300);
                    }
                }

                if (radioButton.getText().equals("UDP")){

                    if (text.equals("1")){
                        new SenderUDP().execute(1);
                    }
                    if (text.equals("10")){
                        new SenderUDP().execute(10);
                    }
                    if (text.equals("50")){
                        new SenderUDP().execute(50);
                    }
                    if (text.equals("100")){
                        new SenderUDP().execute(100);
                    }
                    if (text.equals("200")){
                        new SenderUDP().execute(200);
                    }
                    if (text.equals("300")){
                        new SenderUDP().execute(300);
                    }
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

    public void sendMessageTCP(int repeticiones) {

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
