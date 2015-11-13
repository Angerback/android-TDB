package cl.citiaps.jefferson.taller_android_bd.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author: Jefferson Morales De la Parra
 * Clase que se utiliza para manipular objetos JSON
 */
public class JsonHandler {

    /**
     * Método que recibe un JSONArray en forma de String y devuelve un String[] con los actores
     */
    public String[] getActors(String actors) {
        try {
            JSONArray ja = new JSONArray(actors);
            if(ja == null){
                return null;
            }
            String[] result = new String[ja.length()];
            String actor;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor = " " + row.getString("firstName") + " " + row.getString("lastName");
                result[i] = actor;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " JsonHandler misc " + e.toString());
        }
        return null;
    }// getActors(String actors)
    public ArrayList<String[]> getActorsDetail(String actors) {
        try {
            JSONArray ja = new JSONArray(actors);
            ArrayList<String[]> result = new ArrayList<>();

            //String actor;
            String actor, id, lastName, firstName, lastUpdate;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor = " " + row.getString("firstName") + " " + row.getString("lastName");
                //actor = " " + row.getString("firstName") + " " + row.getString("lastName")+ " "+row.getString("actorId")+ " "+row.getString("lastUpdate");
                firstName = row.getString("firstName");
                lastName = row.getString("lastName");
                id = row.getInt("actorId")+"";
                lastUpdate = row.getString("lastUpdate");
                // result[i] = actor+id+last ;
                result.add(new String[]{actor, lastName, firstName, id, lastUpdate});

            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }

}// JsonHandler