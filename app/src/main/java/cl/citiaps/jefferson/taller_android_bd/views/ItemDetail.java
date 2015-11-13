package cl.citiaps.jefferson.taller_android_bd.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.citiaps.jefferson.taller_android_bd.R;

/**
 * @author: Jefferson Morales De la Parra
 * Clase Fragmento que se utiliza para mostrar el detalle de los items de la lista
 */
public class ItemDetail extends Fragment {

    /**
     * Constructor. Obligatorio para Fragmentos!
     */
    public ItemDetail() {
    }// ItemDetail()

    /**
     * Método que crea la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_detail, container, false);
        Bundle bundle = getArguments();
        ((TextView) v.findViewById(R.id.item_detail)).setText(bundle.getString("item"));

        ((TextView) v.findViewById(R.id.item_detail)).setText(bundle.getString("item"));
        ((TextView) v.findViewById(R.id.item_first_name)).setText(bundle.getStringArray("data")[2]);
        ((TextView) v.findViewById(R.id.item_last_name)).setText(bundle.getStringArray("data")[1]);
        ((TextView) v.findViewById(R.id.item_id)).setText(bundle.getStringArray("data")[3]);
        ((TextView) v.findViewById(R.id.item_last_update)).setText(bundle.getStringArray("data")[4]);

        return v;

    }// onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    /**
     * Método que se llama una vez que se ha restaurado el estado del fragmento
     */
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        ((TextView) getView().findViewById(R.id.item_detail)).setText(bundle.getString("item"));
        super.onViewStateRestored(savedInstanceState);
    }// onViewStateRestored(Bundle savedInstanceState)

}// ItemDetail extends Fragment