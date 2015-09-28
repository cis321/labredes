package com.labredes.grupo15.lab6redes;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by cis on 20/09/15.
 */
public class SenderUDP extends AsyncTask<Integer, Void, String> {



    protected String doInBackground(Integer... repeticiones) {

        for( int i = 0 ; i < repeticiones[0].intValue() ; i++) {
            try {
                Location location = buffer.getLocation();
                String messageStr = i + "," + location.getLatitude() + "," + location.getLongitude() + "," + location.getAltitude() + "," + location.getSpeed();
                InetAddress ip = InetAddress.getByName("157.253.220.83");
                int server_port = 5005;
                int msg_length = messageStr.length();
                byte[] message = messageStr.getBytes();

                DatagramSocket s = new DatagramSocket();

                DatagramPacket p = new DatagramPacket(message, msg_length, ip, server_port);

                s.send(p);

                s.close();

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
