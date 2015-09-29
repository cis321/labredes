package com.labredes.grupo15.lab6redes;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by cis on 20/09/15.
 */
class SenderTCP extends AsyncTask<Integer, Void, String> {



    protected String doInBackground(Integer... repeticiones) {
        try {
            Socket socket = new Socket("192.168.0.17", 5006);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            PrintWriter output = new PrintWriter(out,true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            output.println("HELLO");
            if (reader.readLine().equals("HELLO")) {
                for (int i = 0; i < repeticiones[0].intValue(); i++) {
                    Location location = buffer.getLocation();
                    String message = i + "," + location.getLatitude() + "," + location.getLongitude() + "," + location.getAltitude() + "," + location.getSpeed();
                    Log.i("TCP", "Ubicación: " + message);
                    output.println(message);
                    Log.i("TCP", "Flush!");
                    TimeUnit.SECONDS.sleep(1);
                }
                output.println("GOODBYE");
                out.close();
                socket.close();
                return "Ok";
            }
        }
        catch (Exception e){
            Log.e("UnknownHostException", e.getMessage());

        }
        return "ok";
    }

}
