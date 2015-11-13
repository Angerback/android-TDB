package cl.citiaps.jefferson.taller_android_bd.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cl.citiaps.jefferson.taller_android_bd.R;

/**
 * @author:
 */
public class HttpPost extends AsyncTask<String, Void, String>{
    private Context context;

    /**
     * Constructor
     */
    public HttpPost(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... urls)  {

        String result = "";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type",
                    "application/json");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            String query = "{\"firstName\": \"" + urls[1] +"\", \"lastName\": \"" + urls[2] +"\"}";

            DataOutputStream os = new DataOutputStream (
                    connection.getOutputStream ());

            os.writeBytes(query);
            os.flush();
            os.close();
            os.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();


        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            return "NO_SERVER";
        }

        // 11. return result

    }

    @Override
    protected void onPostExecute(String result) {

        if (result == "NO_SERVER") {
            Toast toast = Toast.makeText(this.context, R.string.no_server,  Toast.LENGTH_LONG);
            toast.show();
        }else{
            /*Intent intent = new Intent("httpPost").putExtra("data", result);
            context.sendBroadcast(intent);*/
            Toast toast = Toast.makeText(this.context, R.string.success_post,  Toast.LENGTH_LONG);
            toast.show();
        }
    }

}// HttpPost