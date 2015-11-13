package cl.citiaps.jefferson.taller_android_bd.views;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author:
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cl.citiaps.jefferson.taller_android_bd.R;
import cl.citiaps.jefferson.taller_android_bd.controllers.HttpPost;
import cl.citiaps.jefferson.taller_android_bd.utilities.SystemUtilities;

public class NewItem extends Fragment implements View.OnClickListener {

    TextView tvIsConnected;
    EditText etNombreActor;
    EditText etApellidoActor;
    Button btnPost;
    private final String URL_POST = "http://192.168.43.35:8080/sakila-backend1860114378085685832/actors";
    private BroadcastReceiver br = null;

    /**
     * Constructor. Obligatorio para Fragmentos!
     */
    public NewItem() {
    }// NewItem()

    /**
     * Método que crea la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_item, container, false);

    }// onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    /**
     * Método que se ejecuta luego que el fragmento es creado o restaurado
     */
    @Override
    public void onResume() {

        // get reference to the views
        //tvIsConnected = (TextView) getActivity().findViewById(R.id.tvIsConnected);
        etNombreActor = (EditText) getActivity().findViewById(R.id.etNombreActor);
        etApellidoActor = (EditText) getActivity().findViewById(R.id.etApellidoActor);
        btnPost = (Button) getActivity().findViewById(R.id.btnPostActor);

        // check if you are connected or not
        /*if (new SystemUtilities(getActivity().getApplicationContext()).isNetworkAvailable()) {
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are connected");
        } else {
            tvIsConnected.setText("You are NOT connected");
        }*/

        // add click listener to Button "POST"
        btnPost.setOnClickListener(this);
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPostActor:
                if(!validate())
                    Toast.makeText(getActivity().getBaseContext(), R.string.no_text, Toast.LENGTH_LONG).show();
                else{
                // call AsynTask to perform network operation on separate thread
                new HttpPost(getActivity().getApplicationContext(), new HttpPost.AsyncResponse(){
                    // Al hacer override se implementa el comportamiento para este listener.
                    @Override
                    public void processFinish(String output){
                        //Here you will receive the result fired from async class
                        //of onPostExecute(result) method.
                        if(output == "SUCCESS"){
                            getActivity().getFragmentManager().popBackStack();
                            //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE))
                                    .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                        }
                    }
                }).execute(URL_POST, etNombreActor.getText().toString(), etApellidoActor.getText().toString());
                }
                // Retorno desde HTTPPOST

                break;
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private boolean validate(){
        if(etNombreActor.getText().toString().trim().equals(""))
            return false;
        else if(etApellidoActor.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

}// NewItem extends Fragment