package com.labredes.grupo15.lab6redes;

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

        for( int i = 0 ; i < repeticiones[0].intValue() ; i++) {
            try {
                Socket socket = new Socket("192.168.0.3", 5006);

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                PrintWriter output = new PrintWriter(out);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String message = i+"-234.444-3455-2645-3";

                output.println("HELLO\n");

                Log.i("TCP", "Ubicación: " + message);
                output.println(message);

                output.println("GOODBYE\n");

                out.flush();
                out.close();
                socket.close();

                TimeUnit.SECONDS.sleep(1);

            } catch (UnknownHostException e){
                Log.e("UnknownHostException", e.getMessage());

            } catch (IOException e){
                Log.e("IOException", e.getMessage());

            } catch (InterruptedException e){
                Log.e("InterruptedException", e.getMessage());

            }
        }

        return "Ok";
    }

}
