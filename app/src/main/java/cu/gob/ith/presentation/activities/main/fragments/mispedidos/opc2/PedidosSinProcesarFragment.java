package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.gob.ith.R;

public class PedidosSinProcesarFragment extends Fragment {


    public PedidosSinProcesarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos_sin_procesar, container, false);
    }
}