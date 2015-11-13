package cl.citiaps.jefferson.taller_android_bd.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Handler;

import cl.citiaps.jefferson.taller_android_bd.MainActivity;
import cl.citiaps.jefferson.taller_android_bd.R;

/**
 * @author: Jefferson Morales De la Parra
 * Clase que se utiliza para realizar peticiones HTTP mediante el método GET
 */
public class HttpGet extends AsyncTask<String, Void, String> {

    private Context context;

    /**
     * Constructor
     */
    public HttpGet(Context context) {
        this.context = context;
    }// HttpGet(Context context)

    /**
     * Método que realiza la petición al servidor
     */

    @Override
    protected String doInBackground(String... urls)  {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (MalformedURLException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (ProtocolException e) {
            Log.e("ERROR", this.getClass().toString() + " no hay server " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", this.getClass().toString() + " Aqui " + e.toString());
            return "NO_SERVER";
        }
        return null;
    }// doInBackground(String... urls)

    /**
     * Método que manipula la respuesta del servidor
     */
    @Override
    protected void onPostExecute(String result) {

        if (result == "NO_SERVER") {
            Toast toast = Toast.makeText(this.context, R.string.no_server,  Toast.LENGTH_LONG);
            toast.show();
        }else{
            Intent intent = new Intent("httpData").putExtra("data", result);
            context.sendBroadcast(intent);
        }
    }// onPostExecute(String result)

}// HttpGet extends AsyncTask<String, Void, String>